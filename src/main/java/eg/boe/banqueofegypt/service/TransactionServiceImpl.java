package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.entity.Status;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
import eg.boe.banqueofegypt.util.ExecutionStack;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static eg.boe.banqueofegypt.entity.Status.*;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    public static final String TOKEN = "BOE-0112-XgF0";
    private final ClientService clientService;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TransactionRetrievalDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(it->
                modelMapper.map(it, TransactionRetrievalDto.class)
        ).toList();
    }
    
    @Override
    @Transactional
    public TransactionRetrievalDto transact(TransactionPreservationDto transactionPreservationDto) {
        Transaction transaction = modelMapper.map(transactionPreservationDto, Transaction.class);
        transaction.setStatus(PENDING.code);
        transaction = transactionRepository.save(transaction);
        BalanceResponse balanceResponse;
        try {
            balanceResponse = clientService.getBalance(
                    new CheckBalanceRequest(TOKEN), transaction.getPayer().getUrl()
            );
        } catch (BusinessException e) {
//            transaction.setStatus(Transaction.Status.INVALID_DATA);
            transactionRepository.save(transaction);
            throw e;
        } catch (Exception e) {
            transaction.setStatus(SRC_TIMEOUT.code);
            transactionRepository.save(transaction);
            throw new BusinessException(408, "Source timeout");
        }

        if (Integer.parseInt(balanceResponse.getBalance()) < Integer.parseInt(transaction.getAmount())) {
            transaction.setStatus(INSUFFICIENT_FUNDS.code);
            transactionRepository.save(transaction);
            throw new BusinessException(403, "Insufficient funds");
        }

        ExecutionStack stack = new ExecutionStack();

        stack.push(clientService.withdraw(
                new WithdrawMoneyRequest(TOKEN, transaction.getAmount()), transaction.getPayer().getUrl()
        ));

        stack.push(clientService.deposit(
                new DepositMoneyRequest(TOKEN, transaction.getAmount()), transaction.getPayee().getUrl()
        ));

        try {
            stack.execute();
        } catch (BusinessException e) {
            transaction.setStatus(INVALID_DATA.code);
            transactionRepository.save(transaction);
            throw e;
        } catch (Exception e) {
            transaction.setStatus(FAILED.code);
            transactionRepository.save(transaction);
            throw new BusinessException(500, "`Something went wrong!`");
        }

        transaction.setStatus(SUCCESS.code);
        return modelMapper.map(transactionRepository.save(transaction), TransactionRetrievalDto.class);
    }
}

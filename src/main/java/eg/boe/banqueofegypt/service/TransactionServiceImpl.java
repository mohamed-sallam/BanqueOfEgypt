package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
import eg.boe.banqueofegypt.util.ExecutionStack;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
    public TransactionRetrievalDto transact(@Valid TransactionPreservationDto transactionPreservationDto) {
        Transaction transaction = new Transaction();
        System.out.println(transaction.toString());
        transaction.setPayer(new Account(transactionPreservationDto.getPayerId()));
        transaction.setPayee(new Account(transactionPreservationDto.getPayeeId()));
        transaction.setDate(new Date(System.currentTimeMillis()));
        transaction.setAmount(transactionPreservationDto.getAmount());
        transaction.setId(0L);
        transaction.setStatus(PENDING.code);
        System.out.println(transaction.toString());
        transaction = transactionRepository.save(transaction);
        transaction = transactionRepository.findById(transaction.getId()).get();
        System.out.println(transaction.toString());
        BalanceResponse balanceResponse;
        try {
            balanceResponse = clientService.getBalance(
                    new CheckBalanceRequest(TOKEN), transaction.getPayer().getUrl()
            );
        } catch (BusinessException e) {
            transaction.setStatus(INVALID_DATA.code);
            transactionRepository.save(transaction);
            throw e;
        } catch (Exception e) {
            transaction.setStatus(SRC_TIMEOUT.code);
            transactionRepository.save(transaction);
            e.printStackTrace();
            throw new BusinessException(408, "Source timeout");
        }

        if (Float.parseFloat(balanceResponse.getBalance()) < Float.parseFloat(transaction.getAmount())) {
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
            throw new BusinessException(500, "Something went wrong!");
        }

        transaction.setStatus(SUCCESS.code);
        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionRetrievalDto.class);
    }
}

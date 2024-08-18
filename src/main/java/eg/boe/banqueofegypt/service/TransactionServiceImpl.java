package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.DepositMoneyRequest;
import eg.boe.banqueofegypt.model.request.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.exception.BusinessException;
import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
import eg.boe.banqueofegypt.util.ExecutionStack;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

import static eg.boe.banqueofegypt.entity.Status.*;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    public static final String TOKEN = "BOE-0112-XgF0";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
    private final ClientService clientService;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TransactionRetrievalDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(it ->
                modelMapper.map(it, TransactionRetrievalDto.class)
        ).toList();
    }

    @Override
    public TransactionRetrievalDto transact(TransactionPreservationDto transactionPreservationDto) {
        if(transactionPreservationDto.getAmount().isEmpty())
            throw new BusinessException(400, "Amount is required");
        if(!NUMBER_PATTERN.matcher(transactionPreservationDto.getAmount()).matches())
            throw new BusinessException(400,"Amount must only contain digits");
        int amount = Integer.parseInt(transactionPreservationDto.getAmount());

        if(amount<= 0)
            throw new BusinessException(400, "Amount must be positive");
        if(amount<= 1000)
            throw new BusinessException(400, "Amount must be more than 1000");
        if(amount >= 100_000_000)
            throw new BusinessException(400,"Amount must be less than 100 Million");
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
                    transaction.getPayer().getUrl()
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

        stack.push(
                clientService.withdraw(
                new WithdrawMoneyRequest(transaction.getAmount()),transaction.getPayer().getUrl())
        );

        stack.push(clientService.deposit(new DepositMoneyRequest(transaction.getAmount()),transaction.getPayee().getUrl()));

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

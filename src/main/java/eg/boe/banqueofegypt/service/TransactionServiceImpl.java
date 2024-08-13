package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.data.dto.DepositMoneyRequest;
import eg.boe.banqueofegypt.data.dto.WithdrawMoneyRequest;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        transaction.setStatus(Transaction.Status.PENDING);
        transaction = transactionRepository.save(transaction);

        clientService.withdraw(
                new WithdrawMoneyRequest(TOKEN, transaction.getAmount()), transaction.getPayer().getUrl()
        );

        clientService.deposit(
                new DepositMoneyRequest(TOKEN, transaction.getAmount()), transaction.getPayee().getUrl()
        );

        // success
        transaction.setStatus(Transaction.Status.SUCCESS);
        return modelMapper.map(transactionRepository.save(transaction), TransactionRetrievalDto.class);
    }
}

package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.db.AccountRepository;
import eg.boe.banqueofegypt.db.TransactionRepository;
import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.model.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(it->
                modelMapper.map(it, TransactionDto.class)
        ).toList();
    }
    
    @Override
    @Transactional
    public TransactionDto transact(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setStatus(Transaction.Status.PENDING);
        //todo transfer


        // success
        transaction.setStatus(Transaction.Status.SUCCESS);
        return modelMapper.map(transactionRepository.save(transaction), TransactionDto.class);
    }
}

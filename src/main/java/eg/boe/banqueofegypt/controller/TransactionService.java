package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.model.dto.TransactionDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction transact(Transaction transaction);

    @Transactional
    TransactionDto transact(TransactionDto transaction);
}

package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.db.TransactionRepository;
import eg.boe.banqueofegypt.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;


    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}

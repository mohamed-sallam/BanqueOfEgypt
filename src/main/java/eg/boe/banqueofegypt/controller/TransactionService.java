package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();


}

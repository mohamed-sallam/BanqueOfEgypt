package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservation;
import eg.boe.banqueofegypt.model.dto.TransactionRetrieval;

import java.util.List;

public interface TransactionService {
    List<TransactionRetrieval> getAllTransactions();

    TransactionRetrieval transact(TransactionPreservation transactionRetrievalDto);
}

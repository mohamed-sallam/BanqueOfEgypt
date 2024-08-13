package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions();

    TransactionDto transact(TransactionDto transactionDto);
}

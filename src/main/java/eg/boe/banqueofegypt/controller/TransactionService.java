package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;

import java.util.Iterator;

public interface TransactionService {
    Iterator<TransactionRetrievalDto> getAllTransactions();

    TransactionRetrievalDto transact(TransactionPreservationDto transactionRetrievalDto);
}

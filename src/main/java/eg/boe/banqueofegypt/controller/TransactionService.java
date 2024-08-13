package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {
    List<TransactionRetrievalDto> getAllTransactions();

    @Transactional
    TransactionRetrievalDto transact(TransactionPreservationDto transactionRetrievalDto);
}

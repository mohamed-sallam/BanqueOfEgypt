package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.TransactionService;
import eg.boe.banqueofegypt.db.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;


}

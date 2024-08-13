package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.entity.Transaction;
import eg.boe.banqueofegypt.model.dto.TransactionDto;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> 084d0d5ac52a1527fe8565e8e4e058745ffceb50

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction transact(Transaction transaction);
}

package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

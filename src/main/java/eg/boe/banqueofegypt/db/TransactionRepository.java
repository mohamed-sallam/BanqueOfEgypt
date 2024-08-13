package eg.boe.banqueofegypt.db;

import eg.boe.banqueofegypt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

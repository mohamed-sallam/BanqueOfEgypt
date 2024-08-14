package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "INSERT INTO transaction (date, status, payee_id, payer_id, amount) VALUES (CURDATE(),#{#transaction.status},#{#transaction.payee.id},#{#transaction.payer.id},#{#transaction.amount})", nativeQuery = true)
    void add(Transaction transaction);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertId();
}

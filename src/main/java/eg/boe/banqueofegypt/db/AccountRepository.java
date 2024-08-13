package eg.boe.banqueofegypt.db;

import eg.boe.banqueofegypt.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

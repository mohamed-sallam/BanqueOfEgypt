package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.model.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();
    AccountDto addAccount(AccountDto accountDto);
    Account getAccountId(Long id);
}

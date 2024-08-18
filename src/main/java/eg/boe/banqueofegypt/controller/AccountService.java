package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountPayload;

import java.util.List;

public interface AccountService {
    List<AccountPayload> getAllAccounts();
    AccountPayload addAccount(AccountPayload accountPayload);
}

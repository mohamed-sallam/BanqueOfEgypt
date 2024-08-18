package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.AccountService;
import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.model.request.BalanceRequest;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.model.dto.AccountPayload;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static eg.boe.banqueofegypt.service.TransactionServiceImpl.TOKEN;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Override
    public List<AccountPayload> getAllAccounts() {
        List<AccountPayload> accounts = accountRepository.findAll().stream().map(s -> modelMapper.map(s, AccountPayload.class)).toList();

        return accounts.stream().map(acc -> {
            BalanceResponse balanceResponse = clientService.getBalance(new BalanceRequest(TOKEN), acc.getUrl());
            acc.setBalance(balanceResponse.getBalance());
            return acc;
        }).toList();
    }

    @Override
    public AccountPayload addAccount(AccountPayload accountPayload) {
        Account account = modelMapper.map(accountPayload, Account.class);
        accountRepository.save(account);
        return modelMapper.map(account, AccountPayload.class);
    }
}

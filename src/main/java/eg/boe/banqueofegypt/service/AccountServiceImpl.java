package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.AccountService;
import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.model.response.BalanceResponse;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.model.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accounts = accountRepository.findAll().stream().map(s -> modelMapper.map(s, AccountDto.class)).toList();

        return accounts.stream().peek(acc -> {
            try {
                BalanceResponse balanceResponse = clientService.getBalance(acc.getUrl());
                acc.setBalance(balanceResponse.getBalance());
            } catch (Exception e) {
                acc.setBalance("N/A");
            }
        }).parallel().toList();
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public Account getAccountId(Long id) {
        return accountRepository.getById(id);
    }
}

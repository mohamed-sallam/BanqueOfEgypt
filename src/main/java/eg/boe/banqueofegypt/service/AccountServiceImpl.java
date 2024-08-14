package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.AccountService;
import eg.boe.banqueofegypt.controller.ClientService;
import eg.boe.banqueofegypt.data.dto.BalanceResponse;
import eg.boe.banqueofegypt.data.dto.CheckBalanceRequest;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.model.dto.AccountDto;
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
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accounts = accountRepository.findAll().stream().map(s -> modelMapper.map(s, AccountDto.class)).toList();

        return accounts.stream().map(acc -> {
            BalanceResponse balanceResponse = clientService.getBalance(new CheckBalanceRequest(TOKEN), acc.getUrl());
            acc.setBalance(balanceResponse.getBalance());
            return acc;
        }).toList();
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }
}

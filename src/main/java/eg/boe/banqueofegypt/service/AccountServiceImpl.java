package eg.boe.banqueofegypt.service;

import eg.boe.banqueofegypt.controller.AccountService;
import eg.boe.banqueofegypt.db.AccountRepository;
import eg.boe.banqueofegypt.entity.Account;
import eg.boe.banqueofegypt.model.dto.AccountDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.
                findAll().
                stream().
                map(s -> modelMapper.map(s,AccountDto.class)).toList();
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account =modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }
}

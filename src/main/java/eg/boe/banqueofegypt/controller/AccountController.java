package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Setter
@Getter
@RequiredArgsConstructor
@Controller
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public String accounts(Model model) {
        model.addAttribute("account", new AccountDto());
        return "registration";
    }
    //todo replace return value to page name
    @GetMapping("/getAllAccounts")
    public String getAllAccounts() {
        return "accounts";
    }
    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("account") AccountDto account , Model model) {
        accountService.addAccount(account);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "registration";
    }
}

package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/registration")
    public String register(@ModelAttribute("account") AccountDto account , Model model) {
        accountService.addAccount(account);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "registration";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("account", new AccountDto());
        return "registration";
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
}

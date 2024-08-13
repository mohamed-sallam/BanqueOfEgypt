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
    @GetMapping("/getAllAccounts")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("account") AccountDto account , Model model) {
        accountService.addAccount(account);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "registration";
    }
    @GetMapping("/registration")
    public String moveToRegistration(Model model) {
        return "registration";
    }
    @GetMapping("/")
    public String moveToIndex(Model model) {
        return "index";
    }
}

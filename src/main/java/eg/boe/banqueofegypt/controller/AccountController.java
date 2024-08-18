package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountPayload;
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

    @PostMapping("/accounts")
    public String createAccount(@ModelAttribute("account") AccountPayload account , Model model) {
        accountService.addAccount(account);
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "registration";
    }
}

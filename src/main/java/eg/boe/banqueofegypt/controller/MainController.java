package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountPayload;
import eg.boe.banqueofegypt.model.dto.TransactionPreservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class MainController {
    AccountService accountService;
    TransactionService transactionService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "index";
    }
    @GetMapping("/accounts")
    public String accounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
    @GetMapping("//transactions")
    public String transactionForm(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("transaction", new TransactionPreservation());
        return "transactions";
    }

    @GetMapping("/registration")
    public String moveToRegistration(Model model) {
        model.addAttribute("account", new AccountPayload());
        return "registration";
    }
}

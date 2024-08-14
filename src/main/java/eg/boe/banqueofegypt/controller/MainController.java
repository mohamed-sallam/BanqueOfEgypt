package eg.boe.banqueofegypt.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@AllArgsConstructor
@Controller
public class MainController {
    AccountService accountService;
    TransactionService transactionService;
    @GetMapping("/dashboard")
    public String home(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "index";
    }
    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
    @GetMapping("/allTransactions")
    public String allTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "tables-data";
    }
    @GetMapping("/createTransaction")
    public String createTransaction(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "transactions";
    }
}

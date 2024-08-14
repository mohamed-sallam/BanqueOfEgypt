package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
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
        model.addAttribute("transaction", new TransactionPreservationDto());
        return "transactions";
    }
    @PostMapping("/createTransaction")
    public String createTransaction(@ModelAttribute TransactionPreservationDto transactionPreservationDto, Model model){
        model.addAttribute("transaction", transactionPreservationDto);
        model.addAttribute("accounts", accountService.getAllAccounts());
        System.out.println(transactionPreservationDto);
        System.out.println(transactionService.transact(transactionPreservationDto));
        return "transactions";
    }
}

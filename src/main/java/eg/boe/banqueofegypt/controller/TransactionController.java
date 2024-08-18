package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservation;
import eg.boe.banqueofegypt.model.dto.TransactionRetrieval;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Setter
@Getter
@RequiredArgsConstructor
@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("//transactions")
    public String makeTransaction(@ModelAttribute TransactionPreservation transactionPreservation, Model model){
        model.addAttribute("transaction", transactionPreservation);
        model.addAttribute("accounts", accountService.getAllAccounts());
        System.out.println(transactionPreservation);
        System.out.println(transactionService.transact(transactionPreservation));
        return "transactions";
    }
}

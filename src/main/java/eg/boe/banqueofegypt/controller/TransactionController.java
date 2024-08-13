package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
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
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public String getAllTransaction(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        //TODO REPLACE WITH CORRECT HTML FILE
        return "transactions";
    }

    @PostMapping
    public String getAllTransaction(@ModelAttribute TransactionRetrievalDto transaction, Model model) {
        //TODO DO Transaction
        model.addAttribute("transactions", transactionService.getAllTransactions());
        //TODO REPLACE WITH CORRECT HTML FILE
        return "transactions";
    }

}

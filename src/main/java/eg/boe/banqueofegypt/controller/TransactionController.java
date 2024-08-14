package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionRetrievalDto;
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



    @GetMapping()
    public String getAllTransaction(@ModelAttribute TransactionRetrievalDto transaction, Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "registration";
    }

}

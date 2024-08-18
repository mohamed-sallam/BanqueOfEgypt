package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.TransactionPreservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class TransactionController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "index";
    }

    @GetMapping("/transact")
    public String transactionForm(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("transaction", new TransactionPreservationDto());
        return "transact";
    }

    @PostMapping("/transact")
    public String makeTransaction(@ModelAttribute TransactionPreservationDto transactionPreservationDto, Model model) {
        System.out.println("incontroller"+transactionPreservationDto);
        model.addAttribute("transaction", transactionPreservationDto);
        model.addAttribute("accounts", accountService.getAllAccounts());
        transactionService.transact(transactionPreservationDto);
        return "redirect:/";
    }
}

package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.model.dto.AccountDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Setter
@Getter
@RequiredArgsConstructor
@Controller
public class AccountController {
    private final AccountService accountService;
    //todo replace return value to page name
    @PostMapping("/addAccount")
    public String addAccount(@RequestBody AccountDto accountDto){
        accountService.addAccount(accountDto);
        return "redirect:/";
    }

    //todo replace return value to page name
 @GetMapping("/getAllAccounts")
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }
}

package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddBalanceDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Bank;
import com.openclassrooms.projet06.service.AccountService;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import com.openclassrooms.projet06.service.BankService;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private AccountService accountService;
    private BankService bankService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    public MainController(AccountService accountService, BankService bankService) {
        this.accountService = accountService;
        this.bankService = bankService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("soldes")
    public AddBalanceDto addBalanceDto() {
        return new AddBalanceDto();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/")
    public String home(Model model) {
        Account account = accountService.getAccount(currentUserName());
        Bank bank = bankService.getBankUser(currentUserName());
        model.addAttribute("account", account);
        model.addAttribute("bank", bank);
        return "index";
    }

    @PostMapping("/addMoney")
    public String addMoney(@ModelAttribute("soldes") AddBalanceDto addBalanceDto) {
        if (addBalanceDto.getAmount()<=0) {
            return "redirect:/?errorAccount";
        }
        if(!this.bankService.checkBankBalance(currentUserName(),addBalanceDto.getAmount())){
            return "redirect:/?balanceErrorAccount";
        }
        this.accountService.setAmount(currentUserName(),addBalanceDto.getAmount());
        this.bankService.setBalance(currentUserName(),-addBalanceDto.getAmount());
        return "redirect:/?successAccount";
    }

    @PostMapping("/transfertMoney")
    public String transfertMoney(@ModelAttribute("soldes") AddBalanceDto addBalanceDto) {
        if (addBalanceDto.getAmount()<=0) {
            return "redirect:/?errorBank";
        }
        if(!this.accountService.checkAccountBalance(currentUserName(),addBalanceDto.getAmount())){
            return "redirect:/?balanceErrorBank";
        }
        this.accountService.setAmount(currentUserName(),-addBalanceDto.getAmount());
        this.bankService.setBalance(currentUserName(),addBalanceDto.getAmount());
        return "redirect:/?successBank";
    }
}
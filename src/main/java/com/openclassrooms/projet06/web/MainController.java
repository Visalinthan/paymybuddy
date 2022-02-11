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

    @ModelAttribute("account")
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
        if (addBalanceDto.getSoldes()<=0) {
            return "redirect:/?error";
        }
        this.accountService.setAmount(currentUserName(),addBalanceDto.getSoldes());
        return "redirect:/?success";
    }
}
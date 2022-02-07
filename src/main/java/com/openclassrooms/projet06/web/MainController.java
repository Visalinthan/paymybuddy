package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddBalanceDto;
import com.openclassrooms.projet06.service.AccountService;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private AccountService accountService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("account")
    public AddBalanceDto addBalanceDto() {
        return new AddBalanceDto();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping
    public String addMoney(@ModelAttribute("amount") AddBalanceDto addBalanceDto) {
       /* if (addBalanceDto.getAmount()>0) {
            return "redirect:/index?error";
        }*/
        Authentication authentication = authenticationFacade.getAuthentication();
        this.accountService.setAmount(authentication.getName(),addBalanceDto.getAmount());
        return "redirect:/index?success";
    }
}
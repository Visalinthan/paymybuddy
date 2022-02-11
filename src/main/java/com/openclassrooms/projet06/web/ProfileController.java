package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddBankDto;
import com.openclassrooms.projet06.model.Bank;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import com.openclassrooms.projet06.service.BankService;
import com.openclassrooms.projet06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;
    private BankService bankService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    public ProfileController(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    @ModelAttribute("bankDetails")
    public AddBankDto addBankDto() {
        return new AddBankDto();
    }

    @GetMapping
    public String profile(Model model) {
        Optional<User> user = userService.getUser(currentUserName());
        Bank bank = bankService.getBankUser(currentUserName());
        model.addAttribute("user",user.get());
        model.addAttribute("bank",bank);
        return "profile";
    }

    @PostMapping
    public String addBankAccount(@ModelAttribute("bankDetails") AddBankDto addBankDto) {
        bankService.saveBank(addBankDto,currentUserName());
        return "redirect:/profile?success";
    }
}

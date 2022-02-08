package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.SendMoneyDto;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import com.openclassrooms.projet06.service.OperationService;
import com.openclassrooms.projet06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transfert")
public class OperationController {

    private OperationService operationService;
    private UserService userService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;


    public OperationController(OperationService operationService, UserService userService) {
        this.operationService = operationService;
        this.userService = userService;
    }

    @GetMapping
    public String transaction(Model model) {
        List<User> contacts = userService.getContacts(currentUserName());
        model.addAttribute("contacts", contacts);
        return "transfert";
    }

    @ModelAttribute("operation")
    public SendMoneyDto sendMoneyDto() {
        return new SendMoneyDto();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    @PostMapping
    public String sendMoneyContact(@ModelAttribute("operation") SendMoneyDto sendMoneyDto) {
        operationService.sendMoney(sendMoneyDto,currentUserName());
        return "redirect:/transfert?success";
    }
}

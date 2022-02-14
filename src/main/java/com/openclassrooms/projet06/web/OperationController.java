package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.SendMoneyDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Operation;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.AccountService;
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
    private AccountService accountService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;


    public OperationController(OperationService operationService, UserService userService, AccountService accountService) {
        this.operationService = operationService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping
    public String transaction(Model model) {
        List<User> contacts = userService.getContacts(currentUserName());
        List<Operation> operations = operationService.getOperationsDetails(currentUserName());
        model.addAttribute("user",currentUserName());
        model.addAttribute("contacts", contacts);
        model.addAttribute("operations", operations);
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
        User userAdmin = this.userService.getUserByRole("ROLE_ADMIN");
        double amountTotal = sendMoneyDto.getAmount()+(sendMoneyDto.getAmount()*0.005);

        if (sendMoneyDto.getAmount()<=0) {
            return "redirect:/transfert?error";
        }
        if(!this.accountService.checkAccountBalance(currentUserName(),amountTotal)){
            return "redirect:/transfert?balanceError";
        }
        operationService.sendMoney(sendMoneyDto,currentUserName());
        sendMoneyDto.setEmail(userAdmin.getEmail());
        sendMoneyDto.setAmount(sendMoneyDto.getAmount()*0.005);
        sendMoneyDto.setDescription("frais de transaction" );
        operationService.sendMoney(sendMoneyDto,currentUserName());
        return "redirect:/transfert?success";
    }
}

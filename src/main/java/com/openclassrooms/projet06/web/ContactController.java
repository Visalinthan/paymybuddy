package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddContactDto;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import com.openclassrooms.projet06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/contact")
public class ContactController{

    private UserService userService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    public ContactController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String contact(Model model) {
        List<User> contacts = userService.getContacts(currentUserName());
        model.addAttribute("contacts", contacts);
        return "contact";
    }

    @ModelAttribute("contact")
    public AddContactDto addContactDto() {
        return new AddContactDto();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    @PostMapping
    public String addContact(@ModelAttribute("contact") AddContactDto addContactDto) {
        if (addContactDto.getEmail() == null) {
            return "redirect:/contact?error";
        }

        if (userService.checkIfContactExist(currentUserName(), addContactDto.getEmail()) ) {
            return "redirect:/contact?AlreadyExist";
        }

        if (!userService.checkIfUserExist(addContactDto.getEmail())) {
            return "redirect:/contact?NotExist";
        }
        userService.addContact(currentUserName(),addContactDto.getEmail());

        return "redirect:/contact?success";
    }


}

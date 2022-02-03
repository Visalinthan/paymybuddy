package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddContactDto;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.ContactService;
import com.openclassrooms.projet06.service.AuthenticationFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/contact")
public class ContactController{

    private final ContactService contactService;

    @Autowired
    private AuthenticationFacadeImpl authenticationFacade;

    public ContactController(ContactService contactService) {
        super();
        this.contactService = contactService;
    }

    @GetMapping
    public String contact() {
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
        User contact = contactService.checkUserExist(addContactDto.getEmail());
        User user = contactService.checkUserExist(currentUserName());
        if (contact.getEmail() != null){
            contactService.save(user,contact);
            return "redirect:/contact?success";
        }else {
            return "redirect:/contact?error";
        }
    }
}

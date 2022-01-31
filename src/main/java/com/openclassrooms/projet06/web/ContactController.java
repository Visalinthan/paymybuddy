package com.openclassrooms.projet06.web;

import com.openclassrooms.projet06.dto.AddContactDto;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.service.ContactService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;
    private UserDetailsService userDetailsService;

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

    @PostMapping
    public String addContact(@ModelAttribute("contact") AddContactDto addContactDto) {
        User contact = contactService.checkUserExist(addContactDto.getEmail());
        //User user = contactService.checkUserExist(addContactDto.getUserConnected());
        //System.out.println(user.getFirstName());
        if (contact.getEmail() != null){
            //contactService.save(user,contact);
            return "redirect:/contact?success";
        }else {
            return "redirect:/contact?error";
        }

    }
}

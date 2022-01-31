package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.mapper.ContactMapper;
import com.openclassrooms.projet06.model.Contact;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public Contact save(User user,User addContact){
        Contact contact = new Contact();
        contact.setId(user.getId());
        contact.addUsers(addContact);
        return contactRepository.save(contact);
    }

    public User checkUserExist(String mail){
        return contactRepository.findByEmail(mail);
    }

    public List<Contact> saveAll(List<Contact> contact){
        return contactRepository.saveAll(contact);
    }

    public List<User> listContact(){
        return contactRepository.findAll();
    }

    public void deleteContact(User user){
        contactRepository.delete(user);
    }
}

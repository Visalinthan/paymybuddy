package com.openclassrooms.projet06.mapper;

import com.openclassrooms.projet06.repository.ContactRepository;
import org.springframework.stereotype.Component;


@Component
public class ContactMapper {

    private final ContactRepository contactRepository;

    public ContactMapper(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

}

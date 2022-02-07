package com.openclassrooms.projet06.service;


import com.openclassrooms.projet06.dto.UserRegistrationDto;
import com.openclassrooms.projet06.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    User addContact(String userConnected, String Contact);

    List<User> getContacts(String email);

    boolean checkIfUserExist(String email);

    boolean checkIfContactExist(String userName, String email);
}
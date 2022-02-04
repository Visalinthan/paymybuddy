package com.openclassrooms.projet06.service;


import com.openclassrooms.projet06.dto.UserRegistrationDto;
import com.openclassrooms.projet06.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    User AddContact(String userConnected, String Contact);

    List<User> getContact(User user);

}

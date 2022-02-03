package com.openclassrooms.projet06.service;


import com.openclassrooms.projet06.dto.UserRegistrationDto;
import com.openclassrooms.projet06.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

}

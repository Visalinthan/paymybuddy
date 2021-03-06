package com.openclassrooms.projet06.service;


import com.openclassrooms.projet06.dto.UserRegistrationDto;
import com.openclassrooms.projet06.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface UserService extends UserDetailsService {

    User saveAdmin(User user);

    User saveUser(UserRegistrationDto registrationDto);

    User addContact(String userConnected, String Contact);

    Optional<User> getUser(String email);

    List<User> getContacts(String email);

    List<User> getAllUsers();

    boolean checkIfUserExist(String email);

    boolean checkIfContactExist(String userName, String email);

    User getUserByRole(String role);
}

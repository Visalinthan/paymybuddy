package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.dto.UserRegistrationDto;
import com.openclassrooms.projet06.model.Role;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AccountService accountService;

    public UserServiceImpl(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                new BCryptPasswordEncoder().encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        this.accountService.saveAccount(user);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user
                .get().getEmail(), user.get().getPassword(), mapRolesToAuthorities(user.get().getRoles()));
    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User addContact(String userConnected, String addContact){
        Optional<User> user = userRepository.findByEmail(userConnected);
        User contact = userRepository.findByEmail(addContact).orElse(null);

        user.ifPresent(u -> u.addContacts(contact));

        return user.map(userRepository::save).orElse(null);
    }


    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getContacts(String email){
      return  userRepository.findByEmail(email).map(u -> u.getContact()).orElse(Collections.emptyList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkIfUserExist(final String email) {
        return  userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean checkIfContactExist(String userName, String email) {
       return userRepository.findByEmail(userName).map(u -> u.getContact().stream().anyMatch(user -> user.getEmail().equals(email))).orElse(false);

    }


}
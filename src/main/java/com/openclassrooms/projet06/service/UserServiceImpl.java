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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                new BCryptPasswordEncoder().encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User AddContact(String userConnected, String addContact){
        User user = userRepository.findByEmail(userConnected);
        User contact = userRepository.findByEmail(addContact);
        user.addUsers(user);
        user.addContacts(contact);
        return userRepository.save(user);
    }

    @Override
    public List<User> getContact(User user){
        List<Long> usersId = userRepository.findContactOfUser(user.getId());
        List<User> users = new ArrayList<>();
        for (Long id : usersId){
            users.add(userRepository.getById(id));
        }
        return users;
    }



}
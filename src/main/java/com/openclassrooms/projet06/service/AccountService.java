package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.repository.AccountRepository;
import com.openclassrooms.projet06.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account saveAccount(User user){
        Account account =new Account();
        account.setSoldes(0);
        account.setUser(user);
        return this.accountRepository.save(account);
    }

    public Account getAccount(String email){
        Optional<User> user = userRepository.findByEmail(email);
        Account account = this.accountRepository.findAccountByUserId(user.get().getId());
        return account;
    }

    public Account setAmount(String email,double amount){
        Optional<User> user = userRepository.findByEmail(email);
        Account account = this.accountRepository.findAccountByUserId(user.get().getId());
        double newBalance = account.getSoldes() + amount;
        account.setSoldes(newBalance);
        return this.accountRepository.save(account);
    }



}

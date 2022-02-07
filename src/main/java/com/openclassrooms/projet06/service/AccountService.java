package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Long userId){
        Account account = this.accountRepository.findAccountByUserId(userId);
        return account;
    }

    public void setAmount(String email,double amount){
        Account account = this.accountRepository.findAccountByUserEmail(email);
        if(account != null){
            double newBalance = account.getSolde() + amount;
            account.setSolde(newBalance);
        }
    }


}

package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.dto.AddBankDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Bank;
import com.openclassrooms.projet06.model.User;
import com.openclassrooms.projet06.repository.AccountRepository;
import com.openclassrooms.projet06.repository.BankRepository;
import com.openclassrooms.projet06.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    private BankRepository bankRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public BankService(BankRepository bankRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Bank saveBank(AddBankDto addBankDto, String email){
        Bank bank = new Bank();
        Optional<User> user = userRepository.findByEmail(email);
        Account account = this.accountRepository.findAccountByUserId(user.get().getId());
        bank.setBankName(addBankDto.getBankName());
        bank.setIban(addBankDto.getIban());
        bank.setSoldes(5000);
        bank.setAccount(account);

        return bankRepository.save(bank);
    }
}

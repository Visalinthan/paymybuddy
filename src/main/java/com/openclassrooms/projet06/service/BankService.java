package com.openclassrooms.projet06.service;

import com.openclassrooms.projet06.dto.AddBankDto;
import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Bank;
import com.openclassrooms.projet06.repository.BankRepository;
import org.springframework.stereotype.Service;


@Service
public class BankService {

    private BankRepository bankRepository;
    private AccountService accountService;

    public BankService(BankRepository bankRepository, AccountService accountService) {
        this.bankRepository = bankRepository;
        this.accountService = accountService;
    }

    public Bank getBankUser(String email){
        Account account = this.accountService.getAccount(email);
        return this.bankRepository.findBankByAccount(account.getId());
    }

    public Bank saveBank(AddBankDto addBankDto, String email){
        Bank bank = new Bank();
        Account account = this.accountService.getAccount(email);
        bank.setBankName(addBankDto.getBankName());
        bank.setIban(addBankDto.getIban());
        bank.setSoldes(5000);
        bank.setAccount(account);

        return bankRepository.save(bank);
    }
}

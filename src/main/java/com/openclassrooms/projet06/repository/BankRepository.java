package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankRepository extends JpaRepository<Bank,Long> {

    @Query("SELECT b FROM Bank b WHERE b.account.id = :id")
    Bank findBankByAccount(Long id);
}

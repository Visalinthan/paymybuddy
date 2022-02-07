package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.user.id = :id")
    Account findAccountByUserId(Long id);

    @Query("SELECT a FROM Account a WHERE a.user.email = :email")
    Account findAccountByUserEmail(String email);
}

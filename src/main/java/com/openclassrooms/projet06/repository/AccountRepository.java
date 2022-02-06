package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}

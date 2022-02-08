package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {
}

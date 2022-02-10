package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.Account;
import com.openclassrooms.projet06.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {
    @Query("SELECT o FROM Operation o WHERE o.accountFrom.user.email = :email OR o.accountTo.user.email = :email")
    List<Operation> findOperationByUser(String email);

}

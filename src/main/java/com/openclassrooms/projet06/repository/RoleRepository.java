package com.openclassrooms.projet06.repository;


import com.openclassrooms.projet06.model.Bank;
import com.openclassrooms.projet06.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByRoleName(String name);
}

package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
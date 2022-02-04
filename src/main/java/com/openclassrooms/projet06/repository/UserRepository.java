package com.openclassrooms.projet06.repository;

import com.openclassrooms.projet06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT * FROM user_contact u WHERE u.id = :id",nativeQuery = true)
    List<Long> findContactOfUser(@Param("id") Long id);
}

package com.example.authservice.repository;

import com.example.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    void deleteByUserName(String userName);

    boolean existsByUserName(String userName);

    Optional<User> findUserByUserName(String username);


}

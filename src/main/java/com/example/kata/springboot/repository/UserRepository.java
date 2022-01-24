package com.example.kata.springboot.repository;

import com.example.kata.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String firstName);
    User getUserByEmail(String email);
}

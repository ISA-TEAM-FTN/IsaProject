package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}


package com.example.isa.repository;

import com.example.isa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
    Optional<User> findOneByEmail(String email);
}

package com.example.isa.service;

import com.example.isa.model.UserCredential;
import com.example.isa.repository.UserCredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    public Optional<UserCredential> getUserCredentialByEmail(String email) {
        return userCredentialRepository.findByEmail(email);
    }
}

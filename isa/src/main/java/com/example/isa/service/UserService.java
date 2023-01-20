package com.example.isa.service;

import com.example.isa.controller.dto.UserRegisterDto;
import com.example.isa.controller.exception.NotFoundException;
import com.example.isa.model.User;
import com.example.isa.model.UserCredential;
import com.example.isa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> get(long id) {
        return userRepository.findById(id);
    }

    public User getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.getByEmail(authentication.getName());
    }

    public Optional<User> getUserEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
    public User register(UserRegisterDto userRegisterDto){
        final User registeredUser = new User();
        registeredUser.setActive(false);
        registeredUser.setEmail(userRegisterDto.getEmail());
        return userRepository.save(registeredUser);
    }

}

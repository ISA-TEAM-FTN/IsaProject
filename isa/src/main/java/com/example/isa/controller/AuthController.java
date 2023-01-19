package com.example.isa.controller;

import com.example.isa.configuration.JwtService;
import com.example.isa.controller.dto.AuthenticationDto;
import com.example.isa.controller.dto.LoginDto;
import com.example.isa.controller.exception.NotFoundException;
import com.example.isa.controller.exception.UnauthorizedException;
import com.example.isa.model.User;
import com.example.isa.model.UserCredential;
import com.example.isa.service.UserCredentialService;
import com.example.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final UserService userService;
    private final UserCredentialService userCredentialService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthController(UserService userService, UserCredentialService userCredentialService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userCredentialService = userCredentialService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<AuthenticationDto> login(@Valid @RequestBody LoginDto loginDto) {
        final UserCredential userCredential = userCredentialService.getUserCredentialByEmail(loginDto.getEmail()).orElseThrow(UnauthorizedException::new);
        if (!passwordEncoder.matches(loginDto.getPassword(), userCredential.getPassword())) {
            throw new UnauthorizedException();
        }
        final User user = userService.get(userCredential.getUserId()).orElseThrow(NotFoundException::new);
        final Instant expirationDate = Instant.now().plus(1, ChronoUnit.DAYS);
        final String authenticationToken = jwtService.createAuthenticationToken(userCredential.getUserId(), expirationDate, user.getUserType());
        final String refreshTokenValue = jwtService.createRefreshToken(userCredential.getUserId(), expirationDate);
        final AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setAccessToken(authenticationToken);
        authenticationDto.setUser(user);
        return new ResponseEntity<>(authenticationDto, HttpStatus.OK);
    }
}

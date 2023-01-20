package com.example.isa.controller;

import com.example.isa.controller.dto.UserRegisterDto;
import com.example.isa.model.User;
import com.example.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/current")
    public ResponseEntity<User> getCurrent() {

        User user = userService.getCurrent();

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        final User user = userService.register(userRegisterDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

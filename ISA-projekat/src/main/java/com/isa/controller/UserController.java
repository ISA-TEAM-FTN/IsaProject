package com.isa.controller;

import com.isa.config.CustomUserDetailsService;
import com.isa.domain.dto.ChangePasswordDTO;
import com.isa.domain.dto.LoginDTO;
import com.isa.domain.dto.LoginResponseDTO;
import com.isa.domain.dto.UserDTO;
import com.isa.domain.model.User;
import com.isa.enums.Role;
import com.isa.security.TokenUtil;
import com.isa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private TokenUtil tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        User user = customUserService.findUserByEmail(loginDTO.getEmail());

        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }

        String token = tokenUtils.generateToken(user.getEmail(), user.getRole().toString());
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(token);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        User user = userService.register(userDTO);

        if(user == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {
        User user = userService.add(userDTO);

        if(user == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/current")
    public ResponseEntity<?> getCurrentUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PutMapping(path = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        return new ResponseEntity<>(userService.changePassword(changePasswordDTO), HttpStatus.OK);
    }

    @PutMapping(path = "/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateProfile(userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/pharmacies")
    public ResponseEntity<?> getPharmacies() {
        return new ResponseEntity<>(userService.findAllByRole(Role.ADMIN_CENTER), HttpStatus.OK);
    }

    @GetMapping(path = "/pharmacy-admins")
    public ResponseEntity<?> getPharmacyAdmins() {
        return new ResponseEntity<>(userService.findAllByRole(Role.USER), HttpStatus.OK);
    }

    @GetMapping(path = "/doctors")
    public ResponseEntity<?> getDoctors() {
        return new ResponseEntity<>(userService.findAllByRole(Role.ADMIN_SYSTEM), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> search(@RequestParam String term, @RequestParam Role role) {
        return new ResponseEntity<>(userService.search(term, role), HttpStatus.OK);
    }
}


package com.example.isa.controller;

import com.example.isa.dto.CenterAccountDto;
import com.example.isa.exception.NotFoundException;
import com.example.isa.exception.UnauthorizedException;
import com.example.isa.model.CenterAccount;
import com.example.isa.model.User;
import com.example.isa.service.CenterAccountService;
import com.example.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/centerAccount")
public class CenterAccountController {

    private final CenterAccountService centerAccountService;
    private final UserService userService;

    @Autowired
    public CenterAccountController(CenterAccountService centerAccountService, UserService userService) {
        this.centerAccountService = centerAccountService;
        this.userService = userService;
    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
//    @PutMapping
//    public ResponseEntity<CenterAccount> update(@RequestBody @Valid CenterAccountDto update, @AuthenticationPrincipal Principal principal) {
//        final User user = userService.findByUsername(principal.getName());
//        final CenterAccount centerAccount = centerAccountService.get(user.getCenterAccount().getId()).orElseThrow(NotFoundException::new);
//        centerAccountService.update(centerAccount, update);
//        return new ResponseEntity<>(centerAccount, HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
//    @GetMapping
//    public ResponseEntity<CenterAccount> get(@AuthenticationPrincipal Principal principal) {
//        final User user = userService.findByUsername(principal.getName());
//        final CenterAccount centerAccount = centerAccountService.get(user.getCenterAccount().getId()).orElseThrow(NotFoundException::new);
//        return new ResponseEntity<>(centerAccount, HttpStatus.OK);
//    }


}

package com.isa.controller;


import com.isa.domain.dto.CenterAccountDto;
import com.isa.domain.dto.SearchDto;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.User;
import com.isa.exception.NotFoundException;
import com.isa.service.CenterAccountService;
import com.isa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/center")
public class CenterAccountController {

    private final CenterAccountService centerAccountService;
    private final UserService userService;

    @Autowired
    public CenterAccountController(CenterAccountService centerAccountService, UserService userService) {
        this.centerAccountService = centerAccountService;
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<CenterAccount> update(@RequestBody @Valid CenterAccountDto update) {
        final User user = userService.getCurrentUser();
        final CenterAccount centerAccount = centerAccountService.get(user.getCenterAccount().getId()).orElseThrow(NotFoundException::new);
        final CenterAccount updated = centerAccountService.update(centerAccount, update);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CenterAccount> get() {
        final User user = userService.getCurrentUser();
        final CenterAccount centerAccount = centerAccountService.get(user.getCenterAccount().getId()).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(centerAccount, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CenterAccount>> list(@RequestBody SearchDto searchDto) {
        final List<CenterAccount> list = centerAccountService.list(searchDto.getCenterName());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}

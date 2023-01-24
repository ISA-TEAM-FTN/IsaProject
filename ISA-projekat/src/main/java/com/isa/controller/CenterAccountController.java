package com.isa.controller;

import com.isa.domain.dto.CenterAccountDto;
import com.isa.domain.dto.SearchDto;
import com.isa.domain.model.Appointment;
import com.isa.domain.model.Blood;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.User;
import com.isa.exception.NotFoundException;
import com.isa.service.AppointmentService;
import com.isa.service.BloodService;
import com.isa.service.CenterAccountService;
import com.isa.service.UserService;
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

    private final AppointmentService appointmentService;

    private final BloodService bloodService;

    @Autowired
    public CenterAccountController(CenterAccountService centerAccountService, UserService userService, AppointmentService appointmentService, BloodService bloodService) {
        this.centerAccountService = centerAccountService;
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.bloodService = bloodService;
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

    @GetMapping(path = "/admins-of-center")
    public ResponseEntity<List<User>> getAdminsOfCenter(@RequestParam long centerAccountId) {
        final CenterAccount centerAccount = centerAccountService.get(centerAccountId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(userService.getAllByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestParam long centerAccountId) {
        final CenterAccount centerAccount = centerAccountService.get(centerAccountId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(appointmentService.getFreeAppointments(centerAccount), HttpStatus.OK);
    }

    @GetMapping("/blood")
    public ResponseEntity<List<Blood>> getBlood(@RequestParam long centerAccountId) {
        final CenterAccount centerAccount = centerAccountService.get(centerAccountId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bloodService.getAllByCenterAccount(centerAccount), HttpStatus.OK);
    }

}

package com.isa.controller;

import com.isa.domain.dto.*;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @PutMapping
    public ResponseEntity<CenterAccount> update(@RequestBody @Valid CenterAccountDto update) {
        final User user = userService.getCurrentUser();
        final CenterAccount centerAccount = centerAccountService.get(user.getCenterAccount().getId()).orElseThrow(NotFoundException::new);
        final CenterAccount updated = centerAccountService.update(centerAccount, update);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping(path = "/admins-of-center/{id}")
    public ResponseEntity<List<User>> getAdminsOfCenter(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(userService.getAllByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping(path = "/appointments/{id}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(appointmentService.getFreeAppointments(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping("/blood-A/{id}")
    public ResponseEntity<Blood> getBloodAType(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bloodService.getAllBloodTypeAByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping("/blood-B/{id}")
    public ResponseEntity<Blood> getBloodBType(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bloodService.getAllBloodTypeBByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping("/blood-AB/{id}")
    public ResponseEntity<Blood> getBloodABType(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bloodService.getAllBloodTypeABByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping("/blood-0/{id}")
    public ResponseEntity<Blood> getBlood0Type(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bloodService.getAllBloodType0ByCenterAccount(centerAccount), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @PostMapping("/create-appointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        final Appointment appointment = appointmentService.create(appointmentDTO);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @PostMapping("/scheduled-appointments")
    public ResponseEntity<List<Appointment>> getScheduledAppointment(@RequestBody SortDto sortDto) {
        final CenterAccount centerAccount = centerAccountService.get(sortDto.getCenterAccountId()).orElseThrow(NotFoundException::new);
        final List<Appointment> appointments = appointmentService.getScheduledAndFinishedAppointments(centerAccount, sortDto.getSort());
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping("/scheduled-appointments/{id}")
    public ResponseEntity<List<Appointment>> getScheduledButNotFinishedAppointments(@PathVariable long id) {
        final CenterAccount centerAccount = centerAccountService.get(id).orElseThrow(NotFoundException::new);
        final List<Appointment> appointments = appointmentService.getScheduledAndNotFinishedAppointments(centerAccount);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @PutMapping("/deny-user")
    public ResponseEntity<Void> deny(@RequestBody DenyUserDto denyUserDto) {
        final Appointment appointment = appointmentService.get(Long.parseLong(denyUserDto.getId())).orElseThrow(NotFoundException::new);
        userService.lowerUserPoints(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_CENTER')")
    @GetMapping(path = "/appointment/{id}")
    public ResponseEntity<?> getAppointement(@PathVariable long id) {
        return new ResponseEntity<>(appointmentService.get(id), HttpStatus.OK);
    }
}

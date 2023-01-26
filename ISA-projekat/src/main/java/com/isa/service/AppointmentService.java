package com.isa.service;

import com.isa.domain.dto.AppointmentDTO;
import com.isa.domain.model.Appointment;
import com.isa.domain.model.CenterAccount;
import com.isa.exception.NotFoundException;
import com.isa.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
    }

    @Transactional
    public Appointment create(AppointmentDTO appointmentDTO) {
        final Appointment appointment = new Appointment();
        appointment.setDuration(Integer.parseInt(appointmentDTO.getDuration()));
        appointment.setAdmin(userService.get(appointmentDTO.getAdminOfCenterId()).orElseThrow(NotFoundException::new));
        appointment.setDateAndTime(Instant.parse(appointmentDTO.getDate()));
        appointment.setCenterAccount(appointment.getAdmin().getCenterAccount());
        appointmentRepository.save(appointment);
        return appointment;
    }

    public List<Appointment> getFreeAppointments(CenterAccount centerAccount) {
        return appointmentRepository.findAllByCenterAccountId(centerAccount.getId()).stream()
                .filter(appointment -> appointment.getPatient() == null)
                .toList();
    }
}

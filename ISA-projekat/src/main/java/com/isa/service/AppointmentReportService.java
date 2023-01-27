package com.isa.service;

import com.isa.domain.dto.AppointmentReportDto;
import com.isa.domain.model.AppointmentReport;
import com.isa.domain.model.Blood;
import com.isa.domain.model.Equipment;
import com.isa.enums.BloodType;
import com.isa.exception.NotFoundException;
import com.isa.repository.AppointmentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppointmentReportService {

    private final AppointmentReportRepository appointmentReportRepository;
    private final BloodService bloodService;

    private final UserService userService;
    private final EquipmentService equipmentService;


    @Autowired
    public AppointmentReportService(AppointmentReportRepository appointmentReportRepository, BloodService bloodService, UserService userService, EquipmentService equipmentService) {
        this.appointmentReportRepository = appointmentReportRepository;
        this.bloodService = bloodService;
        this.userService = userService;
        this.equipmentService = equipmentService;
    }

    @Transactional
    public AppointmentReport create(AppointmentReportDto appointmentReportDto) {
        final AppointmentReport appointmentReport = new AppointmentReport();
        appointmentReport.setBagType(appointmentReportDto.getBagType());
        appointmentReport.setBloodType(BloodType.valueOf(appointmentReportDto.getBloodType()));
        appointmentReport.setBloodAmount(Double.parseDouble(appointmentReportDto.getBloodAmount()));
        appointmentReport.setCopperSulfate(appointmentReportDto.getCopperSulfate());
        final Equipment equipment = equipmentService.get(appointmentReportDto.getEquipmentId()).orElseThrow(NotFoundException::new);
        appointmentReport.setEquipment(equipment);
        appointmentReport.setEndOfGiving(appointmentReportDto.getEndOfGiving());
        appointmentReport.setHeart(appointmentReportDto.getHeart());
        appointmentReport.setHemoglobinometer(appointmentReportDto.getHemoglobinometer());
        appointmentReport.setLungs(appointmentReportDto.getLungs());
        appointmentReport.setNote(appointmentReportDto.getNote());
        appointmentReport.setNoteToDoctor(appointmentReportDto.getNoteToDoctor());
        appointmentReport.setPunctureSite(appointmentReportDto.getPunctureSite());
        appointmentReport.setReasonForPrematureTerminationOfBloodDonation(appointmentReportDto.getReasonForPrematureTerminationOfBloodDonation());
        appointmentReport.setStartOfGiving(appointmentReportDto.getStartOfGiving());
        appointmentReport.setTA(appointmentReportDto.getTA());
        appointmentReport.setTT(appointmentReportDto.getTt());
        appointmentReport.setTV(appointmentReportDto.getTv());
        appointmentReport.setDenied(Boolean.parseBoolean(appointmentReportDto.getDenied()));
        appointmentReport.setReasonForDenying(appointmentReportDto.getReasonForDenying());
        appointmentReport.setEquipmentAmount(Double.parseDouble(appointmentReportDto.getEquipmentAmount()));
        final Blood blood = new Blood();
        blood.setAmount(Double.parseDouble(appointmentReportDto.getBloodAmount()));
        blood.setBloodType(BloodType.valueOf(appointmentReportDto.getBloodType()));
        blood.setCenterAccount(userService.getCurrentUser().getCenterAccount());
        bloodService.create(blood);

        equipment.setAmount(equipment.getAmount() - Double.parseDouble(appointmentReportDto.getEquipmentAmount()));
        equipmentService.save(equipment);

        appointmentReportRepository.save(appointmentReport);
        return appointmentReport;
    }
}

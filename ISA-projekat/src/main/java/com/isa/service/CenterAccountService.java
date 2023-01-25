package com.isa.service;


import com.isa.domain.dto.CenterAccountDto;
import com.isa.domain.model.Appointment;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.Feedback;
import com.isa.repository.CenterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CenterAccountService {

    private final CenterAccountRepository centerAccountRepository;

    private final FeedbackService feedbackService;

    @Autowired
    public CenterAccountService(CenterAccountRepository centerAccountRepository, FeedbackService feedbackService) {
        this.centerAccountRepository = centerAccountRepository;
        this.feedbackService = feedbackService;
    }

    public CenterAccount update(CenterAccount centerAccount, CenterAccountDto centerAccountDto) {
        centerAccount.setName(centerAccountDto.getName());
        centerAccount.setAddress(centerAccountDto.getAddress());
        centerAccount.setDescription(centerAccountDto.getDescription());
        centerAccount.setStartTime(LocalTime.parse(centerAccountDto.getStartTime()));
        centerAccount.setEndTime(LocalTime.parse(centerAccountDto.getEndTime()));
        centerAccountRepository.save(centerAccount);
        return centerAccount;
    }

    public Double getAverageRating(Appointment appointment) {
        final List<Feedback> allByCenterAccount = feedbackService.findAllByAppointment(appointment);
        return allByCenterAccount.stream()
                .mapToDouble(Feedback::getGrade)
                .average()
                .orElse(0D);
    }

    public Optional<CenterAccount> get(long id) {
        return centerAccountRepository.findById(id);
    }

    public List<CenterAccount> list(String name) {
        return centerAccountRepository.findByName(name);
    }
}

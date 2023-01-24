package com.isa.service;


import com.isa.domain.dto.CenterAccountDto;
import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.Feedback;
import com.isa.repository.CenterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        centerAccount.setAddress(centerAccount.getAddress());
        centerAccount.setDescription(centerAccountDto.getDescription());
        centerAccountRepository.save(centerAccount);
        return centerAccount;
    }

    public Double getAverageRating(CenterAccount centerAccount) {
        final List<Feedback> allByCenterAccount = feedbackService.findAllByCenterAccount(centerAccount);
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

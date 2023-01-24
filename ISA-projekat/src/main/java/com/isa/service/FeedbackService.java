package com.isa.service;

import com.isa.domain.model.CenterAccount;
import com.isa.domain.model.Feedback;
import com.isa.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> findAllByCenterAccount(CenterAccount centerAccountId) {
        return feedbackRepository.findByCenterAccount(centerAccountId);
    }
}
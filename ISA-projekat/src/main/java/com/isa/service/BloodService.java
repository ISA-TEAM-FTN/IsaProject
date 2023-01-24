package com.isa.service;

import com.isa.domain.model.Blood;
import com.isa.domain.model.CenterAccount;
import com.isa.repository.BloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodService {

    private final BloodRepository bloodRepository;

    @Autowired
    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    public List<Blood> getAllByCenterAccount(CenterAccount centerAccount) {
        return bloodRepository.findBloodByCenterAccount(centerAccount);
    }
}

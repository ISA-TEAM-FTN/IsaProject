package com.isa.service;

import com.isa.domain.model.Blood;
import com.isa.domain.model.CenterAccount;
import com.isa.enums.BloodType;
import com.isa.repository.BloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodService {

    private final BloodRepository bloodRepository;

    @Autowired
    public BloodService(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    public Blood getAllBloodTypeAByCenterAccount(CenterAccount centerAccount) {
        double sumOfABloodType = bloodRepository.findBloodByCenterAccount(centerAccount).stream()
                .filter(blood -> blood.getBloodType() == BloodType.A)
                .map(Blood::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();

        final Blood bloodAType = new Blood();
        bloodAType.setBloodType(BloodType.A);
        bloodAType.setAmount(sumOfABloodType);
        return bloodAType;
    }

    public Blood getAllBloodTypeBByCenterAccount(CenterAccount centerAccount) {
        double sumOfBBloodType = bloodRepository.findBloodByCenterAccount(centerAccount).stream()
                .filter(blood -> blood.getBloodType() == BloodType.B)
                .map(Blood::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();

        final Blood bloodBType = new Blood();
        bloodBType.setBloodType(BloodType.B);
        bloodBType.setAmount(sumOfBBloodType);
        return bloodBType;
    }

    public Blood getAllBloodTypeABByCenterAccount(CenterAccount centerAccount) {
        double sumOfABBloodType = bloodRepository.findBloodByCenterAccount(centerAccount).stream()
                .filter(blood -> blood.getBloodType() == BloodType.AB)
                .map(Blood::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();

        final Blood bloodABType = new Blood();
        bloodABType.setBloodType(BloodType.AB);
        bloodABType.setAmount(sumOfABBloodType);
        return bloodABType;
    }

    public Blood getAllBloodType0ByCenterAccount(CenterAccount centerAccount) {
        double sumOf0BloodType = bloodRepository.findBloodByCenterAccount(centerAccount).stream()
                .filter(blood -> blood.getBloodType() == BloodType.ZERO)
                .map(Blood::getAmount)
                .mapToDouble(Double::doubleValue)
                .sum();

        final Blood bloodZeroType = new Blood();
        bloodZeroType.setBloodType(BloodType.ZERO);
        bloodZeroType.setAmount(sumOf0BloodType);
        return bloodZeroType;
    }
}

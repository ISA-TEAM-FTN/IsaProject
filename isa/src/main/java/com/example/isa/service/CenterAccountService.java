package com.example.isa.service;

import com.example.isa.dto.CenterAccountDto;
import com.example.isa.model.CenterAccount;
import com.example.isa.repository.CenterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CenterAccountService {

    private final CenterAccountRepository centerAccountRepository;

    @Autowired
    public CenterAccountService(CenterAccountRepository centerAccountRepository) {
        this.centerAccountRepository = centerAccountRepository;
    }

    public CenterAccount update(CenterAccount centerAccount, CenterAccountDto centerAccountDto){
        centerAccount.setName(centerAccountDto.getName());
        centerAccount.setAddress(centerAccount.getAddress());
        centerAccount.setDescription(centerAccountDto.getDescription());
        centerAccountRepository.save(centerAccount);
        return centerAccount;
    }

    public Optional<CenterAccount> get(long id) {
        return centerAccountRepository.findById(id);
    }
}

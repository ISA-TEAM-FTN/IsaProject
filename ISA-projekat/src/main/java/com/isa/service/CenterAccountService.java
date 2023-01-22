package com.isa.service;


import com.isa.domain.dto.CenterAccountDto;
import com.isa.domain.model.CenterAccount;
import com.isa.repository.CenterAccountRepository;
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

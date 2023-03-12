package com.acme.movingtomastery.service;

import com.acme.movingtomastery.entity.MainBankAccount;
import com.acme.movingtomastery.repository.MainBankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainBankAccountService {

    private final MainBankAccountRepository mainBankAccountRepository;

    public MainBankAccount find() {
        return mainBankAccountRepository.find();
    }

    public MainBankAccount update(final MainBankAccount mainBankAccount) {
        return mainBankAccountRepository.update(mainBankAccount);
    }
}

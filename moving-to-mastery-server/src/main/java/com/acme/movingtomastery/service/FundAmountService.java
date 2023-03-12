package com.acme.movingtomastery.service;

import com.acme.movingtomastery.entity.FundAmount;
import com.acme.movingtomastery.repository.FundAmountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundAmountService {

    private final FundAmountRepository fundAmountRepository;

    public FundAmount find() {
        return fundAmountRepository.find();
    }

    public FundAmount update(final FundAmount fundAmount) {
        return fundAmountRepository.update(fundAmount);
    }
}

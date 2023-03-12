package com.acme.movingtomastery.service;

import com.acme.movingtomastery.entity.ContributionAmount;
import com.acme.movingtomastery.repository.ContributionAmountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContributionAmountService {

    private final ContributionAmountRepository contributionAmountRepository;

    public ContributionAmount find() {
        return contributionAmountRepository.find();
    }

    public ContributionAmount update(final ContributionAmount contributionAmount) {
        return contributionAmountRepository.update(contributionAmount);
    }
}

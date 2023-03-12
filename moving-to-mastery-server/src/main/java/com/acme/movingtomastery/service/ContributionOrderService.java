package com.acme.movingtomastery.service;

import com.acme.movingtomastery.dto.ContributionOrderDTO;
import com.acme.movingtomastery.entity.ContributionOrder;
import com.acme.movingtomastery.repository.ContributionOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContributionOrderService {

    private final ContributionOrderRepository contributionOrderRepository;

    public Collection<ContributionOrder> findAll() {
        return contributionOrderRepository.findAll();
    }

    public ContributionOrder create(final ContributionOrderDTO contributionOrderDTO) {
        var contributionOrderFromDatabase = contributionOrderRepository.findByMemberId(contributionOrderDTO.memberId());

        if (!Objects.equals(contributionOrderFromDatabase, null)) {
            log.info("ContributionOrder of member {} already exit", contributionOrderDTO.memberId());
            return null;
        }

        var contributionOrder = contributionOrderDTO.toContributionOrder();
        contributionOrder.setId(UUID.randomUUID());
        return contributionOrderRepository.create(contributionOrder);
    }

    public ContributionOrder update(final ContributionOrder contributionOrder) {
        return contributionOrderRepository.update(contributionOrder);
    }
}

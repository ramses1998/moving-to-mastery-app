package com.acme.movingtomastery.dto;

import com.acme.movingtomastery.entity.ContributionOrder;

public record ContributionOrderDTO(
        String order,
        String memberId
) {

    public ContributionOrder toContributionOrder() {
        return ContributionOrder.builder()
                .id(null)
                .order(order)
                .memberId(memberId)
                .build();
    }
}

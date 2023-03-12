package com.acme.movingtomastery.dto;

import com.acme.movingtomastery.entity.Fine;

public record FineDTO(
        String memberId,
        int amount,
        String currency,
        String creationDate
) {

    public Fine toFine() {
        return Fine.builder()
                .id(null)
                .memberId(memberId)
                .amount(amount)
                .currency(currency)
                .creationDate(creationDate)
                .build();
    }
}

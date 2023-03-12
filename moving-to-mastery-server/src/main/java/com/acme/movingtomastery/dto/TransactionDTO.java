package com.acme.movingtomastery.dto;

import com.acme.movingtomastery.entity.Transaction;

public record TransactionDTO(
        int amount,
        String senderId,
        String receiverId,
        String currency,
        String type,
        String creationDate
) {

    public Transaction toTransaction() {
        return Transaction.builder()
                .id(null)
                .amount(amount)
                .senderId(senderId)
                .receiverId(receiverId)
                .currency(currency)
                .type(type)
                .creationDate(creationDate)
                .build();
    }
}

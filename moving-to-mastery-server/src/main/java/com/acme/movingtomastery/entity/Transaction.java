package com.acme.movingtomastery.entity;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private UUID id;
    private int amount;
    private String senderId;
    private String receiverId;
    private String currency;
    private String type;
    private String creationDate;
}

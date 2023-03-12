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
public class Fine {

    private UUID id;
    private String memberId;
    private int amount;
    private String currency;
    private String creationDate;
}

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
public class ContributionAmount {

    private UUID id;
    private int amount;
    private String currency;
}

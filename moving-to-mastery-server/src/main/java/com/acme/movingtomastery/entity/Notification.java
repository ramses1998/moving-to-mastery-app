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
public class Notification {

    private UUID id;
    private String title;
    private String content;
    private String issuerId;
    private String creationDate;
}

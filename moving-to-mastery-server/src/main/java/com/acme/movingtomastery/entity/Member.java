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
public class Member {

    private UUID id;
    private String firstName;
    private String lastName;
    private String maritalStatus;
    private String countryOfResidence;
    private String phoneNumber;
    private String address;
    private String paypal;
    private String profession;
    private String avatar;
    private String role;
}

package com.acme.movingtomastery.dto;

import com.acme.movingtomastery.entity.Member;

public record MemberDTO(
        String firstName,
        String lastName,
        String maritalStatus,
        String countryOfResidence,
        String phoneNumber,
        String address,
        String paypal,
        String profession,
        String avatar,
        String role
) {

    public Member toMember() {
        return Member.builder()
                .id(null)
                .firstName(firstName)
                .lastName(lastName)
                .maritalStatus(maritalStatus)
                .countryOfResidence(countryOfResidence)
                .phoneNumber(phoneNumber)
                .address(address)
                .paypal(paypal)
                .profession(profession)
                .avatar(avatar)
                .role(role)
                .build();
    }
}

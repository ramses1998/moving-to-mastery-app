package com.acme.movingtomastery.dto;

import com.acme.movingtomastery.entity.Notification;

public record NotificationDTO(

        String title,
        String content,
        String issuerId,
        String creationDate

) {

    public Notification toNotification() {
        return Notification.builder()
                .id(null)
                .title(title)
                .content(content)
                .issuerId(issuerId)
                .creationDate(creationDate)
                .build();
    }
}

package com.acme.movingtomastery.service;

import com.acme.movingtomastery.dto.NotificationDTO;
import com.acme.movingtomastery.entity.Notification;
import com.acme.movingtomastery.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Collection<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Notification create(final NotificationDTO notificationDTO) {
        var notification = notificationDTO.toNotification();
        notification.setId(UUID.randomUUID());
        return notificationRepository.create(notification);
    }

    public Notification update(final Notification notification) {
        return notificationRepository.update(notification);
    }

    public Notification delete(final UUID id) {
        return notificationRepository.delete(id.toString());
    }
}

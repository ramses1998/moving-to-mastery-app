package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.dto.NotificationDTO;
import com.acme.movingtomastery.entity.Notification;
import com.acme.movingtomastery.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static com.acme.movingtomastery.shared.Route.ROUTE_NOTIFICATIONS;

@RestController
@RequestMapping(path = ROUTE_NOTIFICATIONS)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Notification> findAll() {
        return notificationService.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Notification create(@RequestBody final NotificationDTO notificationDTO) {
        return notificationService.create(notificationDTO);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Notification update(@RequestBody final Notification notification) {
        return notificationService.update(notification);
    }

    @DeleteMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Notification delete(@PathVariable final UUID id) {
        return notificationService.delete(id);
    }
}

package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.dto.FineDTO;
import com.acme.movingtomastery.entity.Fine;
import com.acme.movingtomastery.service.FineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static com.acme.movingtomastery.shared.Route.ROUTE_FINES;

@RestController
@RequestMapping(path = ROUTE_FINES)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class FineController {

    private final FineService fineService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Fine> findAll() {
        return fineService.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Fine create(@RequestBody final FineDTO fineDTO) {
        return fineService.create(fineDTO);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Fine update(@RequestBody final Fine fine) {
        return fineService.update(fine);
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Fine delete(@PathVariable final UUID id) {
        return fineService.delete(id);
    }
}

package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.dto.ContributionOrderDTO;
import com.acme.movingtomastery.entity.ContributionOrder;
import com.acme.movingtomastery.service.ContributionOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.acme.movingtomastery.shared.Route.ROUTE_CONTRIBUTION_ORDER;

@RestController
@RequestMapping(path = ROUTE_CONTRIBUTION_ORDER)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ContributionOrderController {

    private final ContributionOrderService contributionOrderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<ContributionOrder> findAll() {
        return contributionOrderService.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ContributionOrder create(@RequestBody final ContributionOrderDTO contributionOrderDTO) {
        return contributionOrderService.create(contributionOrderDTO);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ContributionOrder update(@RequestBody final ContributionOrder contributionOrder) {
        return contributionOrderService.update(contributionOrder);
    }
}

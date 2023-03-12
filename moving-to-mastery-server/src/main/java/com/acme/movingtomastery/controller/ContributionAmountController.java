package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.entity.ContributionAmount;
import com.acme.movingtomastery.service.ContributionAmountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.acme.movingtomastery.shared.Route.ROUTE_CONTRIBUTION_AMOUNT;

@RestController
@RequestMapping(path = ROUTE_CONTRIBUTION_AMOUNT)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ContributionAmountController {

    private final ContributionAmountService contributionAmountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ContributionAmount find() {
        return contributionAmountService.find();
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ContributionAmount update(@RequestBody final ContributionAmount contributionAmount) {
        return contributionAmountService.update(contributionAmount);
    }
}

package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.entity.FundAmount;
import com.acme.movingtomastery.service.FundAmountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.acme.movingtomastery.shared.Route.ROUTE_FUND_AMOUNT;

@RestController
@RequestMapping(path = ROUTE_FUND_AMOUNT)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class FundAmountController {

    private final FundAmountService fundAmountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    FundAmount find() {
        return fundAmountService.find();
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FundAmount update(@RequestBody final FundAmount fundAmount) {
        return fundAmountService.update(fundAmount);
    }
}

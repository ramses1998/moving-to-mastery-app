package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.entity.MainBankAccount;
import com.acme.movingtomastery.service.MainBankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.acme.movingtomastery.shared.Route.ROUTE_MAIN_BANK_ACCOUNT;

@RestController
@RequestMapping(path = ROUTE_MAIN_BANK_ACCOUNT)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class MainBankAccountController {

    private final MainBankAccountService mainBankAccountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    MainBankAccount find() {
        return mainBankAccountService.find();
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    MainBankAccount update(@RequestBody MainBankAccount mainBankAccount) {
        return mainBankAccountService.update(mainBankAccount);
    }
}

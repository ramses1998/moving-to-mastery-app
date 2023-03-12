package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.dto.TransactionDTO;
import com.acme.movingtomastery.entity.Transaction;
import com.acme.movingtomastery.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static com.acme.movingtomastery.shared.Route.ROUTE_TRANSACTIONS;

@RestController
@RequestMapping(path = ROUTE_TRANSACTIONS)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<Transaction> findAll(@RequestParam final Map<String, String> params) {
        if (params.containsKey("type")) {
            return transactionService.findByType(params.get("type"));
        }
        return transactionService.findAll();
    }

    @GetMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Transaction findById(@PathVariable final UUID id) {
        return transactionService.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Transaction create(@RequestBody final TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }

    @DeleteMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Transaction delete(@PathVariable final UUID id) {
        return transactionService.delete(id);
    }
}

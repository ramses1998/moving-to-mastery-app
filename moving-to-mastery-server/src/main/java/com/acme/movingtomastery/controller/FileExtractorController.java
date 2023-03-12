package com.acme.movingtomastery.controller;

import com.acme.movingtomastery.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

import static com.acme.movingtomastery.shared.Route.ROUTE_EXTRACT_FILE;

@RestController
@RequestMapping(path = ROUTE_EXTRACT_FILE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class FileExtractorController {

    private final ContributionAmountService contributionAmountService;
    private final ContributionOrderService contributionOrderService;
    private final FineService fineService;
    private final FundAmountService fundAmountService;
    private final MainBankAccountService mainBankAccountService;
    private final MemberService memberService;
    private final NotificationService notificationService;
    private final TransactionService transactionService;

    @GetMapping
    void saveDataAsJson() throws IOException {
        saveContributionAmount();
        saveContributionOrders();
        saveFines();
        saveFundAmount();
        saveMainBankAccount();
        saveMembers();
        saveNotifications();
        saveTransactions();
    }

    private void saveContributionAmount() throws IOException {
        var contributionAmount = contributionAmountService.find();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(contributionAmount);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/contribution-amount.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveContributionOrders() throws IOException {
        var contributionOrders = contributionOrderService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(contributionOrders);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/contribution-order.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveFines() throws IOException {
        var fines = fineService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(fines);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/fines.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveFundAmount() throws IOException {
        var fundAmount = fundAmountService.find();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(fundAmount);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/fund-repository-amount.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveMainBankAccount() throws IOException {
        var mainBankAccount = mainBankAccountService.find();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(mainBankAccount);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/main-bank-account.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveMembers() throws IOException {
        var members = memberService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(members);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/members.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveNotifications() throws IOException {
        var notifications = notificationService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(notifications);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/notifications.json");
        fileWriter.write(json);
        fileWriter.close();
    }

    private void saveTransactions() throws IOException {
        var transactions = transactionService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(transactions);

        FileWriter fileWriter = new FileWriter("../moving-to-mastery-client/src/data/transactions.json");
        fileWriter.write(json);
        fileWriter.close();
    }
}

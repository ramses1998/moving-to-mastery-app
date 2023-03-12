package com.acme.movingtomastery.service;

import com.acme.movingtomastery.dto.TransactionDTO;
import com.acme.movingtomastery.entity.Transaction;
import com.acme.movingtomastery.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Collection<Transaction> findByType(final String type) {
        return transactionRepository.findByType(type);
    }

    public Transaction findById(final UUID id) {
        return transactionRepository.findById(id.toString());
    }

    public Transaction create(final TransactionDTO transactionDTO) {
        var transaction = transactionDTO.toTransaction();
        transaction.setId(UUID.randomUUID());
        return transactionRepository.create(transaction);
    }

    public Transaction delete(final UUID id) {
        return transactionRepository.delete(id.toString());
    }
}

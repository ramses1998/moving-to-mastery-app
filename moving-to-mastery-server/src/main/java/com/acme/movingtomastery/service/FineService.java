package com.acme.movingtomastery.service;

import com.acme.movingtomastery.dto.FineDTO;
import com.acme.movingtomastery.entity.Fine;
import com.acme.movingtomastery.repository.FineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FineService {

    private final FineRepository fineRepository;

    public Collection<Fine> findAll() {
        return fineRepository.findAll();
    }

    public Fine create(final FineDTO fineDTO) {
        var fine = fineDTO.toFine();
        fine.setId(UUID.randomUUID());
        return fineRepository.create(fine);
    }

    public Fine update(final Fine fine) {
        log.info("Fine Fine servce, {}", fine);
        return fineRepository.update(fine);
    }

    public Fine delete(final UUID id) {
        return fineRepository.delete(id.toString());
    }
}

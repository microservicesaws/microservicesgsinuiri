package com.gsm.charlie.inventory.service;

import com.gsm.charlie.inventory.domain.model.Direct;
import com.gsm.charlie.inventory.domain.repository.DirectRepository;
import com.gsm.charlie.inventory.domain.repository.MovementRepository;
import com.gsm.charlie.inventory.domain.service.DirectService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectServiceImpl implements DirectService {

    @Autowired
    private DirectRepository directRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public List<Direct> getAllDirects() {
        return directRepository.findAll();
    }

    @Override
    public Direct getDirectById(Long directId) {
        return directRepository.findById(directId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Direct", "Id", directId));
    }

    @Transactional
    @Override
    public Direct createDirect(Direct direct) {
        return directRepository.save(direct);
    }

    @Override
    public Direct updateDirect(Long directId, Direct directRequest) {
        Direct direct = directRepository.findById(directId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Direct", "Id", directId));
        return directRepository.save(
                direct.setQuantity(directRequest.getQuantity())
                        .setPrice(directRequest.getPrice()));
    }

    @Override
    public ResponseEntity<?> deleteDirect(Long directId) {
        Direct direct = directRepository.findById(directId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Direct", "Id", directId));

        directRepository.delete(direct);
        return ResponseEntity.ok().build();
    }
}
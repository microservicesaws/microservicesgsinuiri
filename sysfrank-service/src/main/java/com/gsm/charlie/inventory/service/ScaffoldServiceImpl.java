package com.gsm.charlie.inventory.service;

import com.gsm.charlie.inventory.domain.model.Scaffold;
import com.gsm.charlie.inventory.domain.repository.ScaffoldRepository;
import com.gsm.charlie.inventory.domain.service.ScaffoldService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaffoldServiceImpl implements ScaffoldService {

    @Autowired
    private ScaffoldRepository scaffoldRepository;

    @Override
    public List<Scaffold> getAllScaffolds() {
        return scaffoldRepository.findAll();
    }

    @Override
    public Scaffold getScaffoldById(Long scaffoldId) {
        return scaffoldRepository.findById(scaffoldId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Scaffold", "Id", scaffoldId));
    }

    @Override
    public Scaffold createScaffold(Scaffold scaffold) {
        return scaffoldRepository.save(scaffold);
    }

    @Override
    public Scaffold updateScaffold(Long scaffoldId, Scaffold scaffoldRequest) {
        Scaffold scaffold = scaffoldRepository.findById(scaffoldId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Scaffold", "Id", scaffoldId));
        return scaffoldRepository.save(
                scaffold.setName(scaffoldRequest.getName()));
    }

    @Override
    public ResponseEntity<?> deleteScaffold(Long scaffoldId) {
        Scaffold scaffold = scaffoldRepository.findById(scaffoldId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Scaffold", "Id", scaffoldId));

        scaffoldRepository.delete(scaffold);
        return ResponseEntity.ok().build();
    }
}
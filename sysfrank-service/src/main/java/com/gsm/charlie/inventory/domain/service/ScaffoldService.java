package com.gsm.charlie.inventory.domain.service;

import com.gsm.charlie.inventory.domain.model.Scaffold;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScaffoldService {
    List<Scaffold> getAllScaffolds();
    Scaffold getScaffoldById(Long scaffoldId);
    Scaffold createScaffold(Scaffold scaffold);
    Scaffold updateScaffold(Long scaffoldId, Scaffold scaffoldRequest);
    ResponseEntity<?> deleteScaffold(Long scaffoldId);
}

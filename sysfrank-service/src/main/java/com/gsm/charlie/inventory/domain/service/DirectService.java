package com.gsm.charlie.inventory.domain.service;

import com.gsm.charlie.inventory.domain.model.Direct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DirectService {
    List<Direct> getAllDirects();
    Direct getDirectById(Long directId);
    Direct createDirect(Direct direct);
    Direct updateDirect(Long directId, Direct directRequest);
    ResponseEntity<?> deleteDirect(Long directId);
}

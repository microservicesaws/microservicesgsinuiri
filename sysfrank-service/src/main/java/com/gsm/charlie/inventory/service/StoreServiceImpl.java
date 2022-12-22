package com.gsm.charlie.inventory.service;

import com.gsm.charlie.inventory.domain.model.Store;
import com.gsm.charlie.inventory.domain.repository.StoreRepository;
import com.gsm.charlie.inventory.domain.service.StoreService;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store", "Id", storeId));
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long storeId, Store storeRequest) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store", "Id", storeId));
        return storeRepository.save(
                store.setName(storeRequest.getName()));
    }

    @Override
    public ResponseEntity<?> deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store", "Id", storeId));

        storeRepository.delete(store);
        return ResponseEntity.ok().build();
    }
}
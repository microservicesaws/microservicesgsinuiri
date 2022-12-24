package com.gsm.charlie.inventory.domain.service;

import com.gsm.charlie.inventory.domain.model.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    Store getStoreById(Long storeId);
    Store createStore(Store store);
    Store updateStore(Long storeId, Store storeRequest);
    ResponseEntity<?> deleteStore(Long storeId);
}

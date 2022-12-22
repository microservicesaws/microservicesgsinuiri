package com.gsm.charlie.inventory.domain.repository;

import com.gsm.charlie.inventory.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

package com.gsm.charlie.inventory.domain.repository;

import com.gsm.charlie.inventory.domain.model.Direct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectRepository extends JpaRepository<Direct, Long> {
}

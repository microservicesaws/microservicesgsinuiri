package com.gsm.charlie.inventory.domain.repository;

import com.gsm.charlie.inventory.domain.model.Scaffold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaffoldRepository extends JpaRepository<Scaffold, Long> {
}

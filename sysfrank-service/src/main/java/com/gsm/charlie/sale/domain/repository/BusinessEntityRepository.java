package com.gsm.charlie.sale.domain.repository;

import com.gsm.charlie.sale.domain.model.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Long> {
}

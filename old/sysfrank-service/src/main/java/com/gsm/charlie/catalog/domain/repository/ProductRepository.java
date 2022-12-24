package com.gsm.charlie.catalog.domain.repository;

import com.gsm.charlie.catalog.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

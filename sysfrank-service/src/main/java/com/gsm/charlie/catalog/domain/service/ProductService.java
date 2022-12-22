package com.gsm.charlie.catalog.domain.service;

import com.gsm.charlie.catalog.domain.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product productRequest);
    ResponseEntity<?> deleteProduct(Long productId);
}

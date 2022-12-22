package com.gsm.charlie.catalog.service;

import com.gsm.charlie.catalog.domain.model.Product;
import com.gsm.charlie.catalog.domain.repository.ProductRepository;
import com.gsm.charlie.catalog.domain.service.ProductService;
import com.gsm.charlie.inventory.domain.repository.DirectRepository;
import com.gsm.charlie.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DirectRepository directRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "Id", productId));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "Id", productId));

        product.getNicknames().clear();
        product.getNicknames().addAll(productRequest.getNicknames());

        return productRepository.save(
                product.setCode(productRequest.getCode())
                        .setName(productRequest.getName())
                        .setUnitOfMeasurement(productRequest.getUnitOfMeasurement())
                        .setPrice(productRequest.getPrice())
                        .setPriceSale(productRequest.getPriceSale())
                        .setPathImage(productRequest.getPathImage())
                        .setTypeCurrency(productRequest.getTypeCurrency()));
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "Id", productId));

        directRepository.findAll().forEach(x -> {
            if (Objects.equals(x.getProduct().getId(), productId)){
                directRepository.delete(x);
            }
        });

        directRepository.flush();

        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}

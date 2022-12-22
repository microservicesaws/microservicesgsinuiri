package com.gsm.charlie.catalog.controller;

import com.gsm.charlie.catalog.domain.model.Product;
import com.gsm.charlie.catalog.domain.service.ProductService;
import com.gsm.charlie.catalog.resource.ProductResource;
import com.gsm.charlie.catalog.resource.SaveProductResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/multiserviciosfuentes/api/v1")
public class ProductController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductResource> getAllProducts() {
        return productService.getAllProducts()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{productId}")
    public ProductResource getProductById(@PathVariable (value = "productId") Long productId) {
        return convertToResource(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ProductResource createProduct(
            @Valid @RequestBody SaveProductResource resource) {
        Product product = convertToEntity(resource);
        return convertToResource(productService.createProduct(product));
    }

    @PutMapping("/products/{productId}")
    public ProductResource updateProduct(@PathVariable Long productId,
                                   @Valid @RequestBody SaveProductResource resource) {
        Product product = convertToEntity(resource);
        return convertToResource(
                productService.updateProduct(productId, product));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @PostMapping("/products/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file")MultipartFile image){
        if (!image.isEmpty()){
            Path directImages = Paths.get("assets//images//upload");
            String pathAbsolute = directImages.toFile().getAbsolutePath();

            try {
                byte[] bytesImage = image.getBytes();
                Path pathComplete = Paths.get(pathAbsolute + "//" + image.getOriginalFilename());
                Files.write(pathComplete, bytesImage);
                return ResponseEntity.ok().build();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    private Product convertToEntity(SaveProductResource resource) {
        return mapper.map(resource, Product.class);
    }
    private ProductResource convertToResource(Product entity) {
        return mapper.map(entity, ProductResource.class);
    }
}
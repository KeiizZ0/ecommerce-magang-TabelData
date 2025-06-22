// src/main/java/com/example/ecommerce/controller/ProductImageController.java
package com.example.ecommerce.controller;

import com.example.ecommerce.entity.ProductImage;
import com.example.ecommerce.service.ProductImageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products/{productId}/images")
public class ProductImageController {
    private final ProductImageService imageService;

    
    public ProductImageController(ProductImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable Long productId) {
        return imageService.getImagesForProduct(productId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductImage> addImage(
            @PathVariable Long productId,
            @RequestParam String imageUrl,
            @RequestParam(required = false) Boolean isPrimary) {
        
        return imageService.addImageToProduct(productId, imageUrl, isPrimary != null && isPrimary)
            .map(image -> ResponseEntity.status(HttpStatus.CREATED).body(image))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable Long productId,
            @PathVariable Long imageId) {
        
        return imageService.deleteImage(imageId) ?
            ResponseEntity.noContent().build() :
            ResponseEntity.notFound().build();
    }
}
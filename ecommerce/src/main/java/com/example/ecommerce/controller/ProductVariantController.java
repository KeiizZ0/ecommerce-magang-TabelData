package com.example.ecommerce.controller;

import com.example.ecommerce.entity.ProductVariant;
import com.example.ecommerce.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/variants")
public class ProductVariantController {
    @Autowired
    private ProductVariantService productVariantService;

    @GetMapping
    public List<ProductVariant> getProductVariants(@PathVariable Long productId) {
        return productVariantService.getVariantsByProductId(productId);
    }

    @PostMapping
    public ProductVariant addVariant(
            @PathVariable Long productId,
            @RequestBody ProductVariant variant) {
        return productVariantService.addVariant(variant);
    }

    @DeleteMapping("/{variantId}")
    public ResponseEntity<Void> deleteVariant(
            @PathVariable Long productId,
            @PathVariable Long variantId) {
        productVariantService.deleteVariant(variantId);
        return ResponseEntity.noContent().build();
    }
}
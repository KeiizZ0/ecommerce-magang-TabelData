package com.example.ecommerce.service;

import com.example.ecommerce.entity.ProductVariant;
import com.example.ecommerce.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<ProductVariant> getVariantsByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }

    public ProductVariant addVariant(ProductVariant variant) {
        return productVariantRepository.save(variant);
    }

    public void deleteVariant(Long variantId) {
        productVariantRepository.deleteById(variantId);
    }
}
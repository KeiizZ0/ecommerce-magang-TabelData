// src/main/java/com/example/ecommerce/service/ProductImageService.java
package com.example.ecommerce.service;


import com.example.ecommerce.entity.ProductImage;
import com.example.ecommerce.repository.ProductImageRepository;
import com.example.ecommerce.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {
    private final ProductImageRepository imageRepository;
    private final ProductRepository productRepository;

    
    public ProductImageService(ProductImageRepository imageRepository, 
                             ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public Optional<List<ProductImage>> getImagesForProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            return Optional.empty();
        }
        return Optional.of(imageRepository.findByProductId(productId));
    }

    @Transactional
    public Optional<ProductImage> addImageToProduct(Long productId, String imageUrl, Boolean isPrimary) {
        return productRepository.findById(productId)
            .map(product -> {
                ProductImage image = new ProductImage();
                image.setImageUrl(imageUrl);
                
                // Handle primary image logic
                boolean shouldBePrimary = isPrimary || imageRepository.countByProductId(productId) == 0;
                if (shouldBePrimary && !isPrimary) {
                    imageRepository.clearPrimaryFlags(productId);
                }
                image.setIsPrimary(shouldBePrimary);
                
                image.setProduct(product);
                return imageRepository.save(image);
            });
    }

    @Transactional
    public boolean deleteImage(Long imageId) {
        return imageRepository.findById(imageId)
            .map(image -> {
                boolean wasPrimary = image.getIsPrimary();
                Long productId = image.getProduct().getId();
                
                imageRepository.delete(image);
                
                if (wasPrimary) {
                    imageRepository.findFirstByProductIdAndIsPrimaryTrue(productId)
                        .ifPresent(img -> {
                            img.setIsPrimary(true);
                            imageRepository.save(img);
                        });
                }
                return true;
            })
            .orElse(false);
    }
}
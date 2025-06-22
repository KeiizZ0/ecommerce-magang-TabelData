// src/main/java/com/example/ecommerce/repository/ProductImageRepository.java
package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long productId);
    
    Optional<ProductImage> findFirstByProductIdAndIsPrimaryTrue(Long productId);
    
    @Transactional
    @Modifying
    @Query("UPDATE ProductImage pi SET pi.isPrimary = false WHERE pi.product.id = ?1")
    void clearPrimaryFlags(Long productId);
    
    long countByProductId(Long productId);
}
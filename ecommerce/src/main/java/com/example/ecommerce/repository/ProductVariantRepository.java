package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId(Long productId);

    @Modifying
@Query("DELETE FROM ProductVariant pv WHERE pv.product.id = :productId")
void deleteByProductId(@Param("productId") Long productId);
}
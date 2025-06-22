package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByOwnerId(Long ownerId);
    List<Store> findByNameContainingIgnoreCase(String name);
    List<Store> findByActive(Boolean active);
}
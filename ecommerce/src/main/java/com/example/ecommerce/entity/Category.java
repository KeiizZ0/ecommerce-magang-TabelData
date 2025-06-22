package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    
    private Category parent;
    
    @OneToMany(mappedBy = "parent")
    @JsonBackReference
    private List<Category> children;
    
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Product> products;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
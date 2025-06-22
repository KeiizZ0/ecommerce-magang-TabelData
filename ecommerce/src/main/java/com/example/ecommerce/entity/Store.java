package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    private String location;
    
    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;
    
    @Column(columnDefinition = "float default 0.0")
    private Float rating;
    
    private String logoUrl;
    
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private User owner;
    
    @OneToMany(mappedBy = "store")
    @JsonBackReference // Tidak akan diserialisasi
    private List<Product> products;
    
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
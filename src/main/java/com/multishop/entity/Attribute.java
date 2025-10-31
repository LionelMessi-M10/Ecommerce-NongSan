package com.multishop.entity;

import java.util.ArrayList;
import java.util.List;

import com.multishop.enums.ProductAttributeType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "attributes")
public class Attribute {
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; // Màu sắc, kích thước, chất liệu, ...
    
    @Enumerated(EnumType.STRING)
    @Column(name = "product_attribute_type")
    private ProductAttributeType productAttributeType;

    @OneToMany(mappedBy = "attribute", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductAttributeValue> productAttributeValues = new ArrayList<>();
}

package com.multishop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand extends Base {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}

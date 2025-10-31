package com.multishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Entity
@Table(name = "wards")
@Getter
@Setter
public class Ward extends Base { // Phường, xã

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "ward_code")
    private Integer wardCode;

    @Column(name = "ward_name")
    private String wardName;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}

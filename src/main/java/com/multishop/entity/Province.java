package com.multishop.entity;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provinces")
@Getter
@Setter
public class Province extends Base { // Tỉnh, thành phố

	@Serial
	private static final long serialVersionUID = 1L;

	@Column(name = "province_id")
	private Integer provinceId;

	@Column(name = "provice_name")
	private String provinceName;

	@OneToMany(mappedBy = "province", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, orphanRemoval = true)
	private List<District> districts;

}

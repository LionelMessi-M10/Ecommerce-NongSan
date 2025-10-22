package com.multishop.entity;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "districts")
@Getter 
@Setter
public class District extends Base { // Quận, huyện
    
    @Serial
    private static final long serialVersionUID = 1L;
	
	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "district_name")
    private String districtName;
    
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    
    @OneToMany(mappedBy = "district", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Ward> wards;

}


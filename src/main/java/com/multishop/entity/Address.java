package com.multishop.entity;

import com.multishop.enums.AddressType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	
	@Column(name = "address_detail")
    private String addressDetail;
	
	@Column(name = "is_default")
	private Boolean isDefault;
	
	@Column(name = "address_type")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;
    
    @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Order order;
}


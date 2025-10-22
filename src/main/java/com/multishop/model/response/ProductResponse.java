package com.multishop.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductResponse {

	private Long id;
	private String name;
	private Double salePrice;
	private Long saleQuantity;
	private Float stock;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private String status;
	
}

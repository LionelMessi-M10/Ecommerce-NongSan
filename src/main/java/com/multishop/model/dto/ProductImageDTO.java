package com.multishop.model.dto;

import lombok.Data;

@Data
public class ProductImageDTO {

	private Long id;
	private String imageUrl;
	private Boolean isThumbnail;
	private Integer sortOrder;
	private Integer status;
	
}

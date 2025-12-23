package com.multishop.model.dto;

import lombok.Data;

@Data
public class ProductMediaDTO {

	private Long id;
	private String imageUrl;
	private Boolean isThumbnail;
	private Integer sortOrder;
	private Integer status;
	
}

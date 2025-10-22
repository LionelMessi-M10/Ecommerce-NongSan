package com.multishop.model.dto;

import lombok.Data;

@Data
public class ProductImageDTO {

	private Long id;
	private String url;
	private Boolean isThumbnail;
	private Byte status;
	
}

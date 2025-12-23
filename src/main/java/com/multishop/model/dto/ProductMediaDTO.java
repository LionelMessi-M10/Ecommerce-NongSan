package com.multishop.model.dto;

import lombok.Data;

@Data
public class ProductMediaDTO {

	private Long id;
	private String url;
	private String mediaType; // Loại media (image, video, etc.)
	private Boolean isThumbnail;
	private Integer sortOrder;
	private Integer status;
	
}

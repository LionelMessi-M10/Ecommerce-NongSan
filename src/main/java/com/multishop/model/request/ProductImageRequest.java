package com.multishop.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductImageRequest {

  private Long id;
	private MultipartFile imageFile;
	private Boolean isThumbnail;
	private Integer sortOrder;
	private Integer status;

}

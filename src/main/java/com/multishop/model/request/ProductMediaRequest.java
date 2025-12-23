package com.multishop.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductMediaRequest {

	private Long id;
	private MultipartFile mediaFile;
	private String mediaType; // Loại media (image, video, etc.)
	private Boolean isThumbnail;
	private Integer sortOrder;
	private Integer status;

}

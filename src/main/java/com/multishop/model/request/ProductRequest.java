package com.multishop.model.request;

import java.util.List;

import com.multishop.model.dto.ProductAttributeValueDTO;
import com.multishop.model.dto.ProductImageDTO;

import lombok.Data;

@Data
public class ProductRequest {

	private Long id;
	private String name;
	private String description;
	private Double originPrice;
	private Double salePrice;
	private Long saleQuantity;
	private Float discount;
	private Integer stock;
	private Byte status;
	private Long categoryId;
	private List<ProductImageDTO> productImages;
	private List<ProductAttributeValueDTO> attributeValues;
	
}

package com.multishop.model.request;

import java.math.BigDecimal;
import java.util.List;

import com.multishop.model.dto.ProductAttributeValueDTO;
import com.multishop.model.dto.ProductMediaDTO;

import lombok.Data;

@Data
public class ProductRequest {

	private Long id;
	private String name;
	private String description;
	private BigDecimal originPrice;
	private BigDecimal salePrice;
	private BigDecimal discount;
	private Long saleQuantity;
	private Long stock;
	private String sku;
	private BigDecimal weight;
	private String dimensions;
	private String productStatus;
	private Boolean isFeature;
	private Long brandId;
	private Integer status;
	private Long categoryId;
	private List<ProductMediaDTO> productMedias;
	private List<ProductAttributeValueDTO> productAttributeValues;

}

package com.multishop.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.multishop.enums.ProductStatus;

import lombok.Data;

@Data
public class ProductResponse {

	private Long id;
	private String name;
	private BigDecimal originPrice;
	private BigDecimal salePrice;
	private BigDecimal discount;
	private Long saleQuantity;
	private Long stock;
	private String sku;
	private BigDecimal weight;
	private String dimensions;
	private ProductStatus productStatus;
	private Boolean isFeature;
	private BigDecimal averageRating;
	private Integer reviewCount;
	private Long brandId;
	private List<Long> categoryIds;
	private List<ProductImageResponse> productImageResponses;
	private List<AttributeResponse> attributeResponses;
	private List<ReviewResponse> reviewResponses;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private Integer status;
	
}

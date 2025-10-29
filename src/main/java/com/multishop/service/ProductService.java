package com.multishop.service;

import org.springframework.data.domain.Page;

import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.response.ProductResponse;

public interface ProductService {

	Page<ProductResponse> getProductsBySpecification(ProductSearchCriteria productSearchCriteria);
	ProductResponse getProductById(Long id);
}

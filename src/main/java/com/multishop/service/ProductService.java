package com.multishop.service;

import java.util.List;

import com.multishop.model.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();
	ProductResponse getProductById(Long id);
}

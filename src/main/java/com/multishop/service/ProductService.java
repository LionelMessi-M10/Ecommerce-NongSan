package com.multishop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.request.ProductImageRequest;
import com.multishop.model.request.ProductRequest;
import com.multishop.model.response.ProductResponse;

public interface ProductService {

	Page<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

	Page<ProductResponse> getProductsBySpecification(ProductSearchCriteria productSearchCriteria);

	ProductResponse getProductById(Long id);

	ProductResponse createProduct(ProductRequest request, List<ProductImageRequest> productImageRequest);

	ProductResponse updateProduct(Long id, ProductRequest request, List<ProductImageRequest> productImageRequest);

	void deleteProduct(Long id);

}

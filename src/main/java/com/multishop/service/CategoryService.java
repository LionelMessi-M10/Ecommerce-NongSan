package com.multishop.service;

import org.springframework.data.domain.Page;

import com.multishop.model.dto.CategorySearchCriteria;
import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse create(CategoryRequest request);

	CategoryResponse update(Long id, CategoryRequest request);

	CategoryResponse delete(Long id);

	CategoryResponse getById(Long id);

	Page<CategoryResponse> getAll(CategorySearchCriteria categorySearchCriteria);
	Page<CategoryResponse> searchBySpecification(CategorySearchCriteria criteria);
	
}

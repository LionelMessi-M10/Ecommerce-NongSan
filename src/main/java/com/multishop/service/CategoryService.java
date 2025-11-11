package com.multishop.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.model.dto.CategorySearchCriteria;
import com.multishop.model.request.CategoryRequest;
import com.multishop.model.response.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse create(CategoryRequest request, MultipartFile imageCategory);

	CategoryResponse update(Long id, CategoryRequest request, MultipartFile imageCategory);

	CategoryResponse delete(Long id);

	CategoryResponse getById(Long id);

	Page<CategoryResponse> getAll(CategorySearchCriteria categorySearchCriteria);

	Page<CategoryResponse> searchBySpecification(CategorySearchCriteria criteria);
	
}

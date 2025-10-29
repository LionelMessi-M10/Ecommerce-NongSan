package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.multishop.converter.ProductConverter;
import com.multishop.entity.Product;
import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.response.ProductResponse;
import com.multishop.repository.ProductRepository;
import com.multishop.service.ProductService;
import com.multishop.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductConverter productConverter;

	@Override
	public Page<ProductResponse> getProductsBySpecification(ProductSearchCriteria productSearchCriteria) {

		// Specification build từ DTO
		Specification<Product> spec = ProductSpecification.filter(productSearchCriteria);

		// Sort direction
		Sort sort = productSearchCriteria.getSortDir().equalsIgnoreCase("desc")
				? Sort.by(productSearchCriteria.getSortBy()).descending()
				: Sort.by(productSearchCriteria.getSortBy()).ascending();

		Pageable pageable = PageRequest.of(productSearchCriteria.getPageNo(), productSearchCriteria.getPageSize(), sort);

		return productRepository.findAll(spec, pageable).map(productConverter::convertEntityToReponse);
	}

	@Override
	public ProductResponse getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found product by id: " + id));
		return productConverter.convertEntityToReponse(product);
	}

}

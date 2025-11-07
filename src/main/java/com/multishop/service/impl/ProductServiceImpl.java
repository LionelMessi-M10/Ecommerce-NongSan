package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.multishop.converter.ProductConverter;
import com.multishop.entity.Product;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.request.ProductRequest;
import com.multishop.model.response.ProductResponse;
import com.multishop.repository.ProductRepository;
import com.multishop.service.ProductService;
import com.multishop.specification.ProductSpecification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
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
				.orElseThrow(() -> new ResourceNotFoundException("Not found product by id: " + id));
		return productConverter.convertEntityToReponse(product);
	}

	@Override
	public Page<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return productRepository.findAll(pageable).map(productConverter::convertEntityToReponse);
	}

	@Override
	public ProductResponse createProduct(ProductRequest request) {
		Product product = productConverter.convertRequestToEntity(request);
		product = productRepository.save(product);
		return productConverter.convertEntityToReponse(product);
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductRequest request) {
		if (productRepository.findById(id).orElse(null) == null) {
			throw new ResourceNotFoundException("Product not found by id: " + id + " to update !");
		}
		Product existingProduct = productConverter.convertRequestToEntity(request);
		productRepository.save(existingProduct);
		return productConverter.convertEntityToReponse(existingProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
		productRepository.delete(product);
	}

}

package com.multishop.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Category;
import com.multishop.entity.Product;
import com.multishop.enums.ProductStatus;
import com.multishop.model.request.ProductRequest;
import com.multishop.model.response.ProductResponse;
import com.multishop.repository.BrandRepository;
import com.multishop.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductConverter {

	private final ModelMapper modelMapper;
	private final ProductImageConverter productImageConverter;
	private final ProductAttributeValueConverter productAttributeValueConverter;
	private final ReviewConverter reviewConverter;
	private final BrandRepository brandRepository;
	private final CategoryRepository categoryRepository;

	public ProductResponse convertEntityToReponse(Product product) {
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);

		productResponse.setProductStatus(product.getProductStatus().toString());
		productResponse.setBrandId(product.getBrand().getId());
		productResponse.setCategoryIds(product.getCategories().stream().map(category -> category.getId()).toList());
		productResponse
				.setProductImageResponses(product.getImages().stream().map(productImageConverter::convertToResponse).toList());
		productResponse.setProductAttributeValueResponses(product.getProductAttributeValues().stream().map(
				productAttributeValueConverter::convertToResponse).toList());

		if (!product.getReviews().isEmpty()) {
			productResponse
					.setReviewResponses(product.getReviews().stream().map(reviewConverter::convertToResponse).toList());
		}

		return productResponse;
	}

	public Product convertRequestToEntity(ProductRequest productRequest) {
		Product product = modelMapper.map(productRequest, Product.class);

		product.setProductStatus(ProductStatus.valueOf(productRequest.getProductStatus()));

		if (productRequest.getBrandId() != null) {
			product.setBrand(brandRepository.findById(productRequest.getBrandId()).orElse(null));
		}
		if (productRequest.getCategoryId() != null) {
			Set<Category> categories = new HashSet<>();
			Category category = categoryRepository.findById(productRequest.getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category not found by id: " + productRequest.getCategoryId()));
			categories.add(category);
			product.setCategories(categories);
		}
		if (!productRequest.getProductImages().isEmpty()) {
			product
					.setImages(productRequest.getProductImages().stream().map(productImageConverter::convertToEntity).toList());
		}
		if (!productRequest.getProductAttributeValues().isEmpty()) {
			product.setProductAttributeValues(productRequest.getProductAttributeValues().stream()
					.map(productAttributeValueConverter::convertToEntity).toList());
		}

		return product;
	}

}

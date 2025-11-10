package com.multishop.controller.shop;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.response.ProductResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.ProductService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shops/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
		ProductResponse productResponse = productService.getProductById(id);

		if (productResponse == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(ApiResponse.error(HttpStatus.NOT_FOUND, "Not found product by id: " + id, null));
		}

		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Get product successfully by id: " + id, productResponse));
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchProducts(@RequestBody(required = false) ProductSearchCriteria productSearchCriteria) {
		Page<ProductResponse> productsResponse = productService.getProductsBySpecification(productSearchCriteria);
		return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Search product by spec sucessfully !", productsResponse));
	}
	

}

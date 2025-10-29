package com.multishop.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.dto.ProductSearchCriteria;
import com.multishop.model.response.ProductResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/common")
public class ProductController {
  
  private final ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<?> getAllProductBySpecification(@RequestBody(required = false) ProductSearchCriteria productSpecification) {
      Page<ProductResponse> products = productService.getProductsBySpecification(productSpecification);

      if(products.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(HttpStatus.NOT_FOUND, "Not found products", null));
      }

      return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Search found " + products.getSize() + " products", products));
  }


  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Long id) {
      return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Found product by id: " + id, productService.getProductById(id)));
  }
  

}

package com.multishop.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.service.ProductService;
import com.multishop.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/common")
public class ProductController {
  
  private final ProductService productService;

  @GetMapping("/products")
  public String getAllProductBySpecification(@RequestBody(required = false) ProductSpecification productSpecification) {
      return new String();
  }
  

}

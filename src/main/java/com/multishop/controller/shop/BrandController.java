package com.multishop.controller.shop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.model.request.BrandRequest;
import com.multishop.model.request.BrandSearchRequest;
import com.multishop.model.response.BrandResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.BrandService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shop/brands")
public class BrandController {

  private final BrandService brandService;

  @GetMapping
  public ResponseEntity<?> searchBrand(@RequestBody(required = false) BrandSearchRequest brandSearchRequest) {
    Page<BrandResponse> brandResponse = brandService.searchBrand(brandSearchRequest);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Search sucessfully !", brandResponse));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Long id) {
    BrandResponse brandResponse = brandService.getById(id);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Get brand sucessfully !", brandResponse));
  }

  @PostMapping
  public ResponseEntity<?> createBrand(@RequestBody(required = false) BrandRequest brandRequest,
      @RequestParam("image") MultipartFile imageBrand) {
    BrandResponse newBrand = brandService.createBrand(brandRequest, imageBrand);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(HttpStatus.CREATED, "Create brand sucessfully !", newBrand));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateBrand(@PathVariable Long id, @RequestBody(required = false) BrandRequest brandRequest,
      @RequestParam("image") MultipartFile imageBrand) {
    BrandResponse updatedBrand = brandService.updateBrand(id, brandRequest, imageBrand);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Update brand successfully !", updatedBrand));
  }

}

package com.multishop.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.model.request.BrandRequest;
import com.multishop.model.request.BrandSearchRequest;
import com.multishop.model.response.BrandResponse;

public interface BrandService {

  Page<BrandResponse> searchBrand(BrandSearchRequest brandSearchRequest);
  BrandResponse getById(Long id);
  BrandResponse createBrand(BrandRequest brandRequest, MultipartFile multipartFile);
  BrandResponse updateBrand(Long id, BrandRequest brandRequest, MultipartFile multipartFile);
  void deleteBrand(Long id);

}

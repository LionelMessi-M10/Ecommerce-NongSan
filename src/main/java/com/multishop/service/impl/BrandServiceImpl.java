package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.converter.BrandConverter;
import com.multishop.entity.Brand;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.BrandRequest;
import com.multishop.model.request.BrandSearchRequest;
import com.multishop.model.response.BrandResponse;
import com.multishop.repository.BrandRepository;
import com.multishop.service.BrandService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final BrandConverter brandConverter;

  @Override
  public Page<BrandResponse> searchBrand(BrandSearchRequest brandSearchRequest) {
    Pageable pageable = PageRequest.of(brandSearchRequest.getPageNo(), brandSearchRequest.getPageSize());
    Page<Brand> brands = brandRepository.findByStatusAndName(brandSearchRequest.getStatus(),
        brandSearchRequest.getKeySearch(), pageable);

    if (brands.isEmpty()) {
      throw new ResourceNotFoundException("Not found brand !");
    }
    return brands.map(brandConverter::convertToResponse);
  }

  @Override
  public BrandResponse createBrand(BrandRequest brandRequest, MultipartFile multipartFile) {
    if (brandRepository.existsByName(brandRequest.getName())) {
      throw new RuntimeException("Brand is already !");
    }
    Brand newBrand = brandConverter.convertToEntity(brandRequest, multipartFile);
    newBrand = brandRepository.save(newBrand);
    return brandConverter.convertToResponse(newBrand);
  }

  @Override
  public BrandResponse updateBrand(Long id, BrandRequest brandRequest, MultipartFile multipartFile) {
    Brand exitsBrand = brandRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found brand to update by id: " + id));

    if (brandRepository.existsByName(brandRequest.getName())) {
      throw new RuntimeException("Can't update brand with name is already !");
    }
    
    exitsBrand = brandRepository.saveAndFlush(brandConverter.convertToEntity(brandRequest, multipartFile));
    return brandConverter.convertToResponse(exitsBrand);
  }

  @Override
  public void deleteBrand(Long id) {
    brandRepository.deleteById(id);
  }

  @Override
  public BrandResponse getById(Long id) {
    return brandConverter.convertToResponse(brandRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found brand to delete by id: " + id)));
  }

}

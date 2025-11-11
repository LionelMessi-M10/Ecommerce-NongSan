package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.multishop.entity.Brand;
import com.multishop.model.request.BrandRequest;
import com.multishop.model.response.BrandResponse;
import com.multishop.utils.UploadFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BrandConverter {

  private final ModelMapper modelMapper;
  private final UploadFile uploadFile;

  public BrandResponse convertToResponse(Brand brand) {
    BrandResponse brandResponse = modelMapper.map(brand, BrandResponse.class);
    brandResponse.setProductQuantity(brand.getProducts().size());
    return brandResponse;
  }

  public Brand convertToEntity(BrandRequest brandRequest, MultipartFile imageBrand) {
    if (imageBrand != null) {
      brandRequest.setLogo(uploadFile.uploadFile(imageBrand, "brands"));
    }

    Brand brand = modelMapper.map(brandRequest, Brand.class);
    return brand;
  }

}

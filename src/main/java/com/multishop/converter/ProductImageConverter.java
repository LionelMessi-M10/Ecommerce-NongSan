package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.ProductImage;
import com.multishop.model.dto.ProductImageDTO;
import com.multishop.model.response.ProductImageResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductImageConverter {
  
  private final ModelMapper modelMapper;

  public ProductImageResponse convertToResponse(ProductImage productImage) {
    ProductImageResponse productImageResponse = modelMapper.map(productImage, ProductImageResponse.class);
    productImageResponse.setProductId(productImage.getProduct().getId());
    return productImageResponse;
  }

  public ProductImage convertToEntity(ProductImageDTO productImageDTO) {
    ProductImage productImage = modelMapper.map(productImageDTO, ProductImage.class);
    return productImage;
  }

}

package com.multishop.converter;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.ProductImage;
import com.multishop.model.dto.ProductImageDTO;
import com.multishop.model.request.ProductImageRequest;
import com.multishop.model.response.ProductImageResponse;
import com.multishop.utils.UploadFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductImageConverter {
  
  private final ModelMapper modelMapper;
  private final UploadFile uploadFile;

  public ProductImageResponse convertToResponse(ProductImage productImage) {
    ProductImageResponse productImageResponse = modelMapper.map(productImage, ProductImageResponse.class);
    productImageResponse.setProductId(productImage.getProduct().getId());
    return productImageResponse;
  }

  public ProductImage convertToEntity(ProductImageDTO productImageDTO) {
    ProductImage productImage = modelMapper.map(productImageDTO, ProductImage.class);
    return productImage;
  }

  public ProductImageDTO convertToDTO(ProductImageRequest productImageRequest) throws IOException {
    ProductImageDTO productImageDTO = modelMapper.map(productImageRequest, ProductImageDTO.class);
    productImageDTO.setImageUrl(uploadFile.uploadFile(productImageRequest.getImageFile(), "products"));
    return productImageDTO;
  }

}

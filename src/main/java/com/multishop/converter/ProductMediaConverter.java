package com.multishop.converter;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.ProductMedia;
import com.multishop.model.dto.ProductMediaDTO;
import com.multishop.model.request.ProductMediaRequest;
import com.multishop.model.response.ProductMediaResponse;
import com.multishop.utils.UploadFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMediaConverter {
  
  private final ModelMapper modelMapper;
  private final UploadFile uploadFile;

  public ProductMediaResponse convertToResponse(ProductMedia productImage) {
    ProductMediaResponse productImageResponse = modelMapper.map(productImage, ProductMediaResponse.class);
    productImageResponse.setProductId(productImage.getProduct().getId());
    return productImageResponse;
  }

  public ProductMedia convertToEntity(ProductMediaDTO productImageDTO) {
    ProductMedia productImage = modelMapper.map(productImageDTO, ProductMedia.class);
    return productImage;
  }

  public ProductMediaDTO convertToDTO(ProductMediaRequest productImageRequest) throws IOException {
    ProductMediaDTO productImageDTO = modelMapper.map(productImageRequest, ProductMediaDTO.class);
    productImageDTO.setUrl(uploadFile.uploadFile(productImageRequest.getMediaFile(), "products"));
    return productImageDTO;
  }

}

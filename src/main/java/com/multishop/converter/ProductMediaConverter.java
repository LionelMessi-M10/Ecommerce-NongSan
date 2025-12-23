package com.multishop.converter;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.ProductMedia;
import com.multishop.model.request.ProductMediaRequest;
import com.multishop.model.response.ProductMediaResponse;
import com.multishop.utils.UploadFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMediaConverter {

  private final ModelMapper modelMapper;
  private final UploadFile uploadFile;

  public ProductMediaResponse convertToResponse(ProductMedia productMedia) {
    ProductMediaResponse productMediaResponse = modelMapper.map(productMedia, ProductMediaResponse.class);
    productMediaResponse.setProductId(productMedia.getProduct().getId());
    return productMediaResponse;
  }

  public ProductMedia convertToEntity(ProductMediaRequest productMediaRequest) throws IOException {
    ProductMedia productMedia = modelMapper.map(productMediaRequest, ProductMedia.class);
    productMedia.setUrl(uploadFile.uploadFile(productMediaRequest.getMediaFile(), "products"));
    return productMedia;
  }

}

package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.ProductAttributeValue;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.dto.ProductAttributeValueDTO;
import com.multishop.model.response.ProductAttributeValueResponse;
import com.multishop.repository.AttributeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductAttributeValueConverter {

  private final ModelMapper modelMapper;
  private final AttributeRepository attributeRepository;

  public ProductAttributeValueResponse convertToResponse(ProductAttributeValue productAttributeValue) {
    ProductAttributeValueResponse productAttributeValueResponse = modelMapper.map(productAttributeValue,
        ProductAttributeValueResponse.class);

    productAttributeValueResponse.setAttributeId(productAttributeValue.getAttribute().getId());
    productAttributeValueResponse.setProductId(productAttributeValue.getProduct().getId());

    return productAttributeValueResponse;
  }

  public ProductAttributeValue convertToEntity(ProductAttributeValueDTO productAttributeValueDTO) {
    ProductAttributeValue productAttributeValue = modelMapper.map(productAttributeValueDTO, ProductAttributeValue.class);

    if (productAttributeValueDTO.getAttributeId() != null) {
      productAttributeValue.setAttribute(attributeRepository.findById(productAttributeValueDTO.getAttributeId()).orElseThrow(() -> new ResourceNotFoundException("Attribute not found by id: " + productAttributeValueDTO.getAttributeId())));
    }

    return productAttributeValue;
  }

}

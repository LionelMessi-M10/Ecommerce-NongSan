package com.multishop.model.response;

import java.util.List;

import lombok.Data;

@Data
public class AttributeResponse {
  
  private Long id;
  private String name;
  private String productAttributeType;
  private List<ProductAttributeValueResponse> productAttributeValueResponses;

}

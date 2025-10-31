package com.multishop.model.response;

import lombok.Data;

@Data
public class ProductAttributeValueResponse {
  
  private Long id;
  private Long productId;
  private Long attributeId;
  private String value;

}

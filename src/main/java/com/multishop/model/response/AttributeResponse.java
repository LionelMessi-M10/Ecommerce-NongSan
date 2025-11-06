package com.multishop.model.response;

import lombok.Data;

@Data
public class AttributeResponse {
  
  private Long id;
  private String name;
  private String productAttributeType;
  private String productAttributeValue;

}

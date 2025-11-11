package com.multishop.model.response;

import lombok.Data;

@Data
public class BrandResponse {

  private Long id;
  private String name;
  private String logo;
  private Integer productQuantity;

}

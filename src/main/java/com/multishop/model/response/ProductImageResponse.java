package com.multishop.model.response;

import lombok.Data;

@Data
public class ProductImageResponse {

  private Long id;
  private String imageUrl;
  private Boolean isThumbnail;
  private Integer sortOrder;
  private Long productId;

}

package com.multishop.model.response;

import lombok.Data;

@Data
public class ProductMediaResponse {

  private Long id;
  private String imageUrl;
  private String mediaType; // Loại media (image, video, etc.)
  private Boolean isThumbnail;
  private Integer sortOrder;
  private Long productId;

}

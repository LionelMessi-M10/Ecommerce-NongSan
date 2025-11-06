package com.multishop.model.response;

import lombok.Data;

@Data
public class ReviewResponse {

  private Long id;
  private Long userId;
  private Long productId;
  private Integer rating;
  private String comment;
  private Integer status;

}

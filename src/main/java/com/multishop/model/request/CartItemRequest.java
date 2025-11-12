package com.multishop.model.request;

import lombok.Data;

@Data
public class CartItemRequest {

  private Long id;
  private Long cartId;
  private Long productId;
  private Integer quantity;

}

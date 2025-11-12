package com.multishop.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CartResponse {

  private Long id;
  private Integer totalProduct;
  private BigDecimal totalPrice;
  private List<CartItemResponse> cartItemResponses;

}

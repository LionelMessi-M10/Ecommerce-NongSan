package com.multishop.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CartItemResponse {

  private Long id;
  private Long productId;
  private String name;
  private BigDecimal salePrice;
  private Integer quantity;
  private String productStatus;
  private List<String> thumbnails;

}

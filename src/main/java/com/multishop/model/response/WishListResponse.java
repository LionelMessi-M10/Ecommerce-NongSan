package com.multishop.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class WishListResponse {

  private Long id;
  private Long userId;
  private Long productId;
  private List<String> thumbernail;
  private String productName;
  private BigDecimal salePrice;
  private String productStatus;

}

package com.multishop.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderItemResponse {

  private Long id;
  private List<String> thumbnails;
  private String name;
  private Integer quantity;
  private BigDecimal unitPrice; // Giá tại thời điểm đặt hàng
  private BigDecimal subTotal; // = quantity * unitPrice
  private Integer status;

}

package com.multishop.model.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequest {

  private Long id;
  private String transactionId; // Mã giao dịch thanh toán, nếu có
  private BigDecimal amount;
  private String currency;
  private String paymentMethod;
  private String paymentStatus;
  private Long orderId;

}

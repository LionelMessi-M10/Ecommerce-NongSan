package com.multishop.model.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

  private Long id;
  private BigDecimal totalAmount; // Tổng tiền đơn hàng
  private BigDecimal shippingFee; // Phí ship
  private BigDecimal discountAmount; // Tổng tiền giảm từ coupon/voucher
  private BigDecimal finalAmount; // Tổng tiền thanh toán cuối cùng (bao gồm phí vận chuyển, giảm giá)
  private String cancelOrderReason; // Lý do trả hàng
  private String orderStatus; // Trạng thái đơn hàng: quy trình đặt, xử lý, giao hàng, huỷ đơn
  private List<OrderItemRequest> orderItemRequests;
  private AddressRequest addressRequest;
  private PaymentRequest paymentRequest;

}

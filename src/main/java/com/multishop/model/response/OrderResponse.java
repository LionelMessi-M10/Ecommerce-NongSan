package com.multishop.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {

	private Long id;
	private BigDecimal totalAmount; // Tổng tiền đơn hàng
	private BigDecimal shippingFee; // Phí ship
	private BigDecimal discountAmount; // Tổng tiền giảm từ coupon/voucher
	private BigDecimal finalAmount; // Tổng tiền thanh toán cuối cùng (bao gồm phí vận chuyển, giảm giá)
	private String cancelOrderReason; // Lý do trả hàng
	private String orderStatus; // Trạng thái đơn hàng
	private Long userId;
	private String userName;
	private String orderAddress;
	private String paymentMehod;
	private String paymentStatus;
	private List<OrderItemResponse> orderItemResponses;

}

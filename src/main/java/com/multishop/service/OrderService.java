package com.multishop.service;

import org.springframework.data.domain.Page;

import com.multishop.model.dto.OrderSearchCriteria;
import com.multishop.model.request.OrderRequest;
import com.multishop.model.response.OrderResponse;

import jakarta.mail.MessagingException;

public interface OrderService {

	OrderResponse createOrder(OrderRequest orderRequest) throws MessagingException;

	OrderResponse updateOrder(Long orderId, OrderRequest orderRequest);

	Page<OrderResponse> getAllOrder(OrderSearchCriteria orderSearchCriteria);

}

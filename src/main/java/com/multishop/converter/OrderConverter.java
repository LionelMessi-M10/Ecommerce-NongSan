package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Order;
import com.multishop.enums.OrderStatus;
import com.multishop.model.request.OrderRequest;
import com.multishop.model.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderConverter {

  private final ModelMapper modelMapper;
  private final OrderItemConverter orderItemConverter;
  private final AddressConverter addressConverter;
  private final PaymentConverter paymentConverter;

  public OrderResponse convertToResponse(Order order) {
    OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

    orderResponse.setOrderStatus(order.getOrderStatus().name());
    orderResponse.setUserId(order.getUser().getId());
    orderResponse.setUserName(order.getUser().getUserName());
    orderResponse.setOrderAddress(order.getAddress().getAddressDetail());
    orderResponse.setPaymentMehod(order.getPayment().getPaymentMethod().getDisplayName());
    orderResponse.setPaymentStatus(order.getPayment().getPaymentStatus().getDisplayName());
    orderResponse
        .setOrderItemResponses(order.getDetails().stream().map(orderItemConverter::convertToResponse).toList());

    return orderResponse;
  }

  public Order convertToEntity(OrderRequest orderRequest) {
    Order order = modelMapper.map(orderRequest, Order.class);

    order.setOrderStatus(OrderStatus.valueOf(orderRequest.getOrderStatus()));

    order.setDetails(orderRequest.getOrderItemRequests().stream().map(orderItemConverter::convertToEntity).toList());

    order.setAddress(addressConverter.convertToEntity(orderRequest.getAddressRequest()));

    order.setPayment(paymentConverter.convertToEntity(orderRequest.getPaymentRequest()));

    return order;
  }

}

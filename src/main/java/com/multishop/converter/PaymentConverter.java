package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Payment;
import com.multishop.enums.PaymentMethod;
import com.multishop.enums.PaymentStatus;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.PaymentRequest;
import com.multishop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PaymentConverter {

  private final ModelMapper modelMapper;
  private final OrderRepository orderRepository;

  public Payment convertToEntity(PaymentRequest paymentRequest) {
    Payment payment = modelMapper.map(paymentRequest, Payment.class);

    payment.setPaymentMethod(PaymentMethod.valueOf(paymentRequest.getPaymentMethod()));
    payment.setPaymentStatus(PaymentStatus.valueOf(paymentRequest.getPaymentStatus()));
    payment.setOrder(orderRepository.findById(paymentRequest.getOrderId()).orElseThrow(
        () -> new ResourceNotFoundException("Payment not found by order id: " + paymentRequest.getOrderId()))
    );

    return payment;
  }

}

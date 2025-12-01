package com.multishop.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.dto.OrderSearchCriteria;
import com.multishop.model.request.OrderRequest;
import com.multishop.model.response.OrderResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.OrderService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/web/orders")
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<?> getAllOrders(@RequestBody(required = false) OrderSearchCriteria orderSearchCriteria) {
    Page<OrderResponse> orders = orderService.getAllOrder(orderSearchCriteria);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Get all orders successfully !", orders));
  }

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody(required = false) OrderRequest orderRequest)
      throws MessagingException {
    OrderResponse newOrder = orderService.createOrder(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(HttpStatus.CREATED, "Create order successfully !", newOrder));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateOrder(@PathVariable Long id,
      @RequestBody(required = false) OrderRequest orderRequest) {
    OrderResponse updatedOrder = orderService.updateOrder(id, orderRequest);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Update order successfully !", updatedOrder));
  }

}

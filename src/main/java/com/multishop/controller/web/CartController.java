package com.multishop.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.request.CartItemRequest;
import com.multishop.model.response.CartResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/web/carts")
public class CartController {

  private final CartService cartService;

  @GetMapping
  public ResponseEntity<?> getAllCartItem() {
    return ResponseEntity
        .ok(ApiResponse.success(HttpStatus.OK, "Get all cart successfully !", cartService.getAllCartItem()));
  }

  @GetMapping("/total")
  public ResponseEntity<?> getTotalItemCart() {
    return ResponseEntity
        .ok(ApiResponse.success(HttpStatus.OK, "Get total items of cart !", cartService.getTotalCartItem()));
  }

  @PostMapping
  public ResponseEntity<?> addProductToCart(@RequestBody(required = false) CartItemRequest cartItemRequest) {
    CartResponse cartResponse = cartService.addProductToCart(cartItemRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(HttpStatus.CREATED, "Add product to cart sucessfully !", cartResponse));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProductFromCart(@PathVariable Long id,
      @RequestBody(required = false) CartItemRequest cartItemRequest) {
    CartResponse cartResponse = cartService.updateCartItem(cartItemRequest);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Update cart successfully !", cartResponse));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCartItem(@PathVariable Long id,
      @RequestBody(required = false) CartItemRequest cartItemRequest) {
    cartService.deleteProductFromCart(id, cartItemRequest.getProductId());
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Delete cart item successfully !", null));
  }

}

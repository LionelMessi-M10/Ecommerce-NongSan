package com.multishop.service;

import com.multishop.entity.User;
import com.multishop.model.request.CartItemRequest;
import com.multishop.model.response.CartResponse;

public interface CartService {

  CartResponse createCartForUser(User user);

  Integer getTotalCartItem();

  CartResponse addProductToCart(CartItemRequest cartItemRequest);

  void deleteProductFromCart(Long cartId, Long productId);

  CartResponse getAllCartItem();

  CartResponse updateCartItem(CartItemRequest cartItemRequest);

}

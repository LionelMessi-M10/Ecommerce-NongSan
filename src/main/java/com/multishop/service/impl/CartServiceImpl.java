package com.multishop.service.impl;

import org.springframework.stereotype.Service;
import com.multishop.converter.CartConverter;
import com.multishop.entity.Cart;
import com.multishop.entity.CartItem;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.CartItemRequest;
import com.multishop.model.response.CartResponse;
import com.multishop.repository.CartItemRepository;
import com.multishop.repository.CartRepository;
import com.multishop.service.CartService;
import com.multishop.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final UserService userService;
  private final CartConverter cartConverter;

  @Override
  public Integer getTotalCartItem() {
    return userService.getCurrentUser().getCart().getCartItems().size();
  }

  @Override
  public CartResponse addProductToCart(CartItemRequest cartItemRequest) {
    Cart cart = cartConverter.convertToEntity(cartItemRequest);
    cart = cartRepository.save(cart);
    return cartConverter.convertToResponse(cart);
  }

  @Override
  public void deleteProductFromCart(Long cartId, Long productId) {
    CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new ResourceNotFoundException("Cart item not found to delete !"));
    cartItemRepository.delete(cartItem);
  }

  @Override
  public CartResponse getAllCartItem() {
    Cart cart = cartRepository.findByUserId(userService.getCurrentUser().getId()).orElseThrow(() -> new ResourceNotFoundException("Cart not found by: " + userService.getCurrentUser().getUserName()));
    return cartConverter.convertToResponse(cart);
  }

  @Override
  public CartResponse updateCartItem(CartItemRequest cartItemRequest) {
    Cart cart = cartConverter.convertToEntity(cartItemRequest);
    cart = cartRepository.saveAndFlush(cart);
    return cartConverter.convertToResponse(cart);
  }

}

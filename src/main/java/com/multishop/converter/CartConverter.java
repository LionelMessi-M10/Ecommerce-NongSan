package com.multishop.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Cart;
import com.multishop.entity.CartItem;
import com.multishop.entity.Product;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.CartItemRequest;
import com.multishop.model.response.CartItemResponse;
import com.multishop.model.response.CartResponse;
import com.multishop.repository.CartRepository;
import com.multishop.repository.ProductRepository;
import com.multishop.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CartConverter {

  private final ModelMapper modelMapper;
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final UserService userService;

  public Cart convertToEntity(CartItemRequest cartRequest) {
    Cart cart = cartRepository.findByUserId(userService.getCurrentUser().getId())
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found !"));
    List<CartItem> cartItems = cart.getCartItems();

    Product product = productRepository.findById(cartRequest.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found add to card !"));

    // Tìm product có sẵn trong cart, nếu có thì update thông tin
    Optional<CartItem> optionalCartItem = cartItems.stream()
        .filter(cartItem -> cartItem.getProduct().getId().equals(cartRequest.getId()))
        .findFirst();

    if (optionalCartItem.isPresent()) {
      Long cartItemId = optionalCartItem.get().getId(); // Lưu ID vào biến
      cartItems.forEach(item -> {
        if (item.getId().equals(cartItemId)) { // Sử dụng equals để so sánh
          item.setQuantity(cartRequest.getQuantity());
        }
      });
    } else {
      CartItem newCartItem = new CartItem();
      newCartItem.setCart(cart);
      newCartItem.setProduct(product);
      newCartItem.setQuantity(cartRequest.getQuantity());
      cartItems.add(newCartItem);
    }

    cart.setCartItems(cartItems);
    cart.setTotalProduct(cartItems.size());

    BigDecimal totalPrice = cartItems.stream()
        .map(cartItem -> cartItem.getProduct().getSalePrice().multiply(new BigDecimal(cartItem.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    cart.setTotalPrice(totalPrice); // Cập nhật totalPrice cho Cart

    return cart;
  }

  public CartResponse convertToResponse(Cart cart) {
    CartResponse cartResponse = modelMapper.map(cart, CartResponse.class);

    if (!cart.getCartItems().isEmpty()) {
      cartResponse.setCartItemResponses(cart.getCartItems().stream().map(cartItem -> {
        CartItemResponse cartItemResponse = modelMapper.map(cartItem, CartItemResponse.class);

        Product product = cartItem.getProduct();

        cartItemResponse.setProductId(product.getId());
        cartItemResponse.setName(product.getName());
        cartItemResponse.setSalePrice(product.getSalePrice());
        cartItemResponse.setProductStatus(product.getProductStatus().toString());
        cartItemResponse.setThumbnails(product.getImages().stream().filter(image -> image.getIsThumbnail())
            .map(image -> image.getImageUrl()).toList());

        return cartItemResponse;
      }).toList());
    }

    return cartResponse;
  }

}

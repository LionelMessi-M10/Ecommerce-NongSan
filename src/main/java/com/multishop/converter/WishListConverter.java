package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Product;
import com.multishop.entity.User;
import com.multishop.entity.WishList;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.response.WishListResponse;
import com.multishop.repository.ProductRepository;
import com.multishop.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WishListConverter {

  private final ModelMapper modelMapper;
  private final UserService userService;
  private final ProductRepository productRepository;

  public WishListResponse convertToResponse(WishList wishList) {
    WishListResponse wishListResponse = modelMapper.map(wishList, WishListResponse.class);

    wishListResponse.setUserId(wishList.getUser().getId());
    wishListResponse.setProductId(wishList.getProduct().getId());
    wishListResponse.setProductName(wishList.getProduct().getName());
    wishListResponse.setSalePrice(wishList.getProduct().getSalePrice());
    wishListResponse.setProductStatus(wishList.getProduct().getProductStatus().toString());

    wishListResponse.setThumbernail(wishList.getProduct().getProductMedias().stream()
        .filter(imageItem -> imageItem.getIsThumbnail())
        .map(imageItem -> imageItem.getUrl())
        .toList());

    return wishListResponse;
  }

  public WishList convertToEntity(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found to add wishlish !"));
    User user = userService.getCurrentUser();

    WishList wishList = new WishList();

    wishList.setProduct(product);
    wishList.setUser(user);

    return wishList;
  }

}

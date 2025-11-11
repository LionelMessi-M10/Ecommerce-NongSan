package com.multishop.service;

import org.springframework.data.domain.Page;

import com.multishop.model.response.WishListResponse;

public interface WishListService {

  Page<WishListResponse> getAllWishList(int pageNo, int pageSize);

  WishListResponse addProductToWishlist(Long productId);

  void removeProductFromWishList(Long id);
}

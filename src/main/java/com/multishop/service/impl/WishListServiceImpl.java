package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.multishop.converter.WishListConverter;
import com.multishop.entity.WishList;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.response.WishListResponse;
import com.multishop.repository.WishListRepository;
import com.multishop.service.UserService;
import com.multishop.service.WishListService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class WishListServiceImpl implements WishListService {

  private final WishListRepository wishListRepository;
  private final WishListConverter wishListConverter;
  private final UserService userService;

  @Override
  public Page<WishListResponse> getAllWishList(int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<WishList> wishListPage = wishListRepository.findByUserId(userService.getCurrentUser().getId(), pageable);

    if (wishListPage.isEmpty()) {
      throw new ResourceNotFoundException("Data not found !");
    }

    return wishListPage.map(wishListConverter::convertToResponse);
  }

  @Override
  public WishListResponse addProductToWishlist(Long productId) {
    WishList newWishList = wishListConverter.convertToEntity(productId);
    newWishList = wishListRepository.save(newWishList);
    return wishListConverter.convertToResponse(newWishList);
  }

  @Override
  public void removeProductFromWishList(Long id) {
    WishList wishList = wishListRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No wishlist to delete !"));
    wishListRepository.delete(wishList);
  }

}

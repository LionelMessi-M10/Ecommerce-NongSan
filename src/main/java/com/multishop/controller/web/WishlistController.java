package com.multishop.controller.web;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.response.WishListResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.WishListService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/web/wishlists")
public class WishlistController {

  private final WishListService wishListService;

  @GetMapping
  public ResponseEntity<?> getAllWishList(@RequestParam(required = false, defaultValue = "1") int pageNo,
      @RequestParam(required = false, defaultValue = "10") int pageSize) {
    Page<WishListResponse> wishListPage = wishListService.getAllWishList(pageNo, pageSize);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Get all wishlist successfully !", wishListPage));
  }

  @PostMapping
  public ResponseEntity<?> addProductToWishList(@RequestParam("id") Long productId) {
    WishListResponse newWishList = wishListService.addProductToWishlist(productId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(HttpStatus.CREATED, "Add wishlist sucessfully !", newWishList));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> removeWishList(@PathVariable Long id) {
    wishListService.removeProductFromWishList(id);
    return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "Remove wishlist successsfully !", null));
  }

}

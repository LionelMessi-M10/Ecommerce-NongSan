package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.Review;
import com.multishop.model.response.ReviewResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReviewConverter {
  
  private final ModelMapper modelMapper;

  public ReviewResponse convertToResponse(Review review) {
    ReviewResponse reviewResponse = modelMapper.map(review, ReviewResponse.class);

    reviewResponse.setUserId(review.getUser().getId());
    reviewResponse.setProductId(review.getProduct().getId());

    return reviewResponse;
  }

}

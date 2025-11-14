package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.multishop.entity.OrderDetail;
import com.multishop.model.request.OrderItemRequest;
import com.multishop.model.response.OrderItemResponse;
import com.multishop.repository.CartItemRepository;
import com.multishop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderItemConverter {

	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;

	public OrderItemResponse convertToResponse(OrderDetail orderDetail) {
		OrderItemResponse orderItemResponse = modelMapper.map(orderDetail, OrderItemResponse.class);

		orderItemResponse.setName(orderDetail.getProduct().getName());
		orderItemResponse.setThumbnails(orderDetail.getProduct().getImages().stream()
				.filter(product -> product.getIsThumbnail())
				.map(product -> product.getImageUrl()).toList());

		return orderItemResponse;
	}

	public OrderDetail convertToEntity(OrderItemRequest orderItemRequest) {
		OrderDetail orderDetail = modelMapper.map(orderItemRequest, OrderDetail.class);

		if (orderItemRequest.getCartId() == null) {
			orderDetail.setProduct(productRepository.findById(orderItemRequest.getProductId()).orElse(null));
		} else {
			orderDetail.setProduct(cartItemRepository.findByCartIdAndProductId(orderItemRequest.getCartId(), orderItemRequest.getProductId()).orElse(null).getProduct());
		}

		return orderDetail;
	}

}

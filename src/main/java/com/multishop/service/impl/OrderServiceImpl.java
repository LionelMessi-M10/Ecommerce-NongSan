package com.multishop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.multishop.converter.OrderConverter;
import com.multishop.entity.Order;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.dto.OrderSearchCriteria;
import com.multishop.model.request.OrderRequest;
import com.multishop.model.response.OrderResponse;
import com.multishop.repository.OrderRepository;
import com.multishop.service.EmailService;
import com.multishop.service.OrderService;
import com.multishop.specification.OrderSpecification;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderConverter orderConverter;
	private final EmailService emailService;
	private final OrderRepository orderRepository;

	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) throws MessagingException {
		Order order = orderConverter.convertToEntity(orderRequest);
		order = orderRepository.save(order);

		// Gửi email xác nhận
		String subject = "Xác nhận đơn hàng #" + order.getId() + " - MultiShop";

		String content = "<h2>🛒 Cảm ơn bạn đã đặt hàng tại <strong>MultiShop</strong>!</h2>"
				+ "<p><strong>Mã đơn hàng:</strong> #" + order.getId() + "</p>"
				+ "<p><strong>Ngày đặt:</strong> " + order.getCreatedDate() + "</p>"
				+ "<p><strong>Tổng tiền:</strong> " + order.getFinalAmount() + " VND</p>"
				+ "<hr/>"
				+ "<p>Chúng tôi sẽ xử lý đơn hàng và giao đến bạn trong thời gian sớm nhất.</p>"
				+ "<p>Trân trọng,</p>"
				+ "<p><b>Đội ngũ MultiShop</b></p>";

		emailService.sendOrderConfirmation("luongthanhhuy@gmail.com", order.getUser().getEmail(), subject, content);

		return orderConverter.convertToResponse(order);
	}

	@Override
	public OrderResponse updateOrder(Long orderId, OrderRequest orderRequest) {
		Order updateOrder = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found by order id: " + orderId));
		updateOrder = orderConverter.convertToEntity(orderRequest);
		updateOrder = orderRepository.saveAndFlush(updateOrder);
		return orderConverter.convertToResponse(updateOrder);
	}

	@Override
	public Page<OrderResponse> getAllOrder(OrderSearchCriteria orderSearchCriteria) {
		Specification<Order> spec = OrderSpecification.filter(orderSearchCriteria);

		// Sort direction
		Sort sort = orderSearchCriteria.getSortDir().equalsIgnoreCase("desc")
				? Sort.by(orderSearchCriteria.getSortBy()).descending()
				: Sort.by(orderSearchCriteria.getSortBy()).ascending();

		Pageable pageable = PageRequest.of(orderSearchCriteria.getPageNo(), orderSearchCriteria.getPageSize(), sort);

		return orderRepository.findAll(spec, pageable).map(orderConverter::convertToResponse);
	}

}

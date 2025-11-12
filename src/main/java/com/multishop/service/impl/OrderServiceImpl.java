package com.multishop.service.impl;

import org.springframework.stereotype.Service;

import com.multishop.entity.Order;
import com.multishop.repository.OrderRepository;
import com.multishop.service.EmailService;
import com.multishop.service.OrderService;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final EmailService emailService;
	private final OrderRepository orderRepository;

	@Override
	public void placeOrder(Order order) throws MessagingException {
		// Lưu đơn hàng
		orderRepository.save(order);

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
	}

}

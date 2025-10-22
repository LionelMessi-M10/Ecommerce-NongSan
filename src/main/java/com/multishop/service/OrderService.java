package com.multishop.service;

import com.multishop.entity.Order;

import jakarta.mail.MessagingException;

public interface OrderService {

	void placeOrder(Order order) throws MessagingException;
}

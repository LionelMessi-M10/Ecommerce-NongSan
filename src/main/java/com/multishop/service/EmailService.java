package com.multishop.service;

import jakarta.mail.MessagingException;

public interface EmailService {

	void sendOrderConfirmation(String fromEmail, String toEmail, String subject, String content) throws MessagingException;
}

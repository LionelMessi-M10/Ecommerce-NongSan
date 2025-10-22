package com.multishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.multishop.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

	@Override
	public void sendOrderConfirmation(String fromEmail, String toEmail, String subject, String content) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(content, true); // true để gửi HTML
        helper.setFrom(fromEmail);

        mailSender.send(message);
	}

}

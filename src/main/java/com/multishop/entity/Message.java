package com.multishop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends Base {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private User senderUser;

	@ManyToOne
	@JoinColumn(name = "receive_id", nullable = false)
	private User receiveUser;

	// @ManyToOne
	// @JoinColumn(name = "shop_id")
	// private Shop shop;

	// @ManyToOne
	// @JoinColumn(name = "user_id")
	// private User user;

	@Lob
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "sent_at", nullable = false)
	private LocalDateTime sendAt; // Thoi gian gui tin nhan

	@Column(name = "is_read")
	private Boolean isRead;
}

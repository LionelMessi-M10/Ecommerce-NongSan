package com.multishop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "sessions")
public class Sessions extends Base {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "expires_at", nullable = false)
	private LocalDateTime expiresAt;

	@Column(name = "device_info")
	private String deviceInfo; // Thông tin thiết bị để kiểm tra tính hợp lệ của phiên

	@Column(name = "is_valid")
	private Boolean isValid; // Cho phép vô hiệu hóa phiên từ phía server (ví dụ: khi người dùng đổi mật khẩu)

}

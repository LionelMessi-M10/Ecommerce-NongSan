package com.multishop.payload;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

	private int status;
	private String message;
	private T data;
	private LocalDateTime timestamp;

	// factory method tiện lợi
	public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
		return new ApiResponse<>(status.value(), message, data, LocalDateTime.now());
	}
	
	public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status.value(), message, data, LocalDateTime.now());
    }
	
}

package com.multishop.controller.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multishop.model.response.ProvinceResponse;
import com.multishop.payload.ApiResponse;
import com.multishop.service.GhnService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/web")
public class GhnApi {

	private final GhnService ghnService;

	@PostMapping("/province")
	public void handleSaveProvince() {
		ghnService.saveProvine();
	}

	@GetMapping("/province")
	public ResponseEntity<ApiResponse<List<ProvinceResponse>>> getAllProvince() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(HttpStatus.OK, "Get province success", ghnService.getAllProvince()));
	}

}

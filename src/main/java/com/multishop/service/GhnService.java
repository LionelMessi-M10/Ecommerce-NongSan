package com.multishop.service;

import java.util.List;

import com.multishop.model.response.ProvinceResponse;

public interface GhnService {
	
	List<ProvinceResponse> getAllProvince();
	
	void saveProvine();
	void saveDistrict();
	void saveWard();
	
}
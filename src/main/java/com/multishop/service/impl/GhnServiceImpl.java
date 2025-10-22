package com.multishop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.multishop.converter.ProvinceConverter;
import com.multishop.entity.Province;
import com.multishop.model.response.ProvinceResponse;
import com.multishop.repository.ProvinceRepository;
import com.multishop.service.GhnService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GhnServiceImpl implements GhnService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private ProvinceConverter provinceConverter;

	@Value("${ghn.token}")
	private String ghnToken;

	@Override
	public List<ProvinceResponse> getAllProvince() {
		List<Province> provinces = provinceRepository.findAll();
		return provinces.stream().map(item -> provinceConverter.convertEntityToResponse(item)).collect(Collectors.toList());
	}

	@Override
	public void saveProvine() {
		String url = "https://online-gateway.ghn.vn/shiip/public-api/master-data/province";

		HttpHeaders headers = new HttpHeaders();
		headers.set("token", ghnToken);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));

		HttpEntity<Void> request = new HttpEntity<>(headers);

		ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, request,
				new ParameterizedTypeReference<>() {
				});

		List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");

		List<Province> provinces = data.stream().map(item -> {
			return provinceConverter.convertDTOToEntity(item);
		}).collect(Collectors.toList());

		provinceRepository.saveAll(provinces);
	}

	@Override
	public void saveDistrict() {
		

	}

	@Override
	public void saveWard() {
		

	}

}

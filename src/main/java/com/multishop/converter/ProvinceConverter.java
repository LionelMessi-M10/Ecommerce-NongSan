package com.multishop.converter;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Province;
import com.multishop.model.response.ProvinceResponse;

@Configuration
public class ProvinceConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Province convertDTOToEntity(Map<String, Object> item) {		
		Province entity = new Province();
        entity.setProvinceId((Integer) item.get("ProvinceID"));
        entity.setProvinceName((String) item.get("ProvinceName"));
        return entity;
	}
	
	public ProvinceResponse convertEntityToResponse(Province province) {
		return modelMapper.map(province, ProvinceResponse.class);
	}
	
}

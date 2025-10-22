package com.multishop.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinceResponse {

	@JsonProperty("province_id")
	private Integer provinceId;

	@JsonProperty("province_name")
	private String provinceName;
	
}

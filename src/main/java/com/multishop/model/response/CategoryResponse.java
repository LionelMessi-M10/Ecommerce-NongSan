package com.multishop.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryResponse {

	private Long id;
	private String name;
	private String image;
	private Integer status;
	private Long categoryParentId;
	private String categoryParentName;
	private Integer level;
	private String path;
	private Long shopId;
	private String shopName;

	private List<CategoryResponse> children; // đệ quy
}

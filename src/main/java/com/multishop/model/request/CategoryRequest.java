package com.multishop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryRequest {
	
	private String name;
    private String image;
    private Long parentId;   // id category cha
	private Integer status;
	
}

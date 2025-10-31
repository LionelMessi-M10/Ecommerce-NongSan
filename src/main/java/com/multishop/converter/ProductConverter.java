package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multishop.entity.Product;
import com.multishop.model.request.ProductRequest;
import com.multishop.model.response.ProductResponse;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductResponse convertEntityToReponse(Product product) {
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		
		return productResponse;
	}

	public Product convertRequestToEntity(ProductRequest productRequest) {
		Product product = modelMapper.map(productRequest, Product.class);

		return product;
	}
	
}

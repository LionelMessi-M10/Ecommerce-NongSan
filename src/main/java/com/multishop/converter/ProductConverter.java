package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Product;
import com.multishop.model.response.ProductResponse;

@Configuration
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductResponse convertEntityToReponse(Product product) {
		ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
		
		if(product.getStatus() == 0) {
			productResponse.setStatus("In Active");
		}
		else if(product.getStock() > 0 && product.getStatus() == 1) {
			productResponse.setStatus("Active");
		}
		else if(product.getStock() == 0) {
			productResponse.setStatus("Out Stock");
		}
		
		return productResponse;
	}
	
}

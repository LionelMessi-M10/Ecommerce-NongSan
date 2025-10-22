package com.multishop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_images")
@Getter @Setter
public class ProductImage extends Base {

    @Serial
    private static final long serialVersionUID = 1L;
	
	@Column(name = "image_url")
    private String imageUrl;
	
	@Column(name = "is_thumbnail")
    private Boolean isThumbnail; // Có phải ảnh đại diện hay không
	
	@Column(name = "sort_order")
	private Integer sortOrder; // Thứ tự hiển thị

    @ManyToOne 
    @JoinColumn(name = "product_id")
    private Product product;
}


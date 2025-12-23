package com.multishop.entity;

import java.io.Serial;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "return_order_items")
public class ReturnOrderItem extends Base {

	@Serial
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "return_order_id", nullable = false)
	private ReturnOrder returnOrder;
	
	@ManyToOne
	@JoinColumn(name = "return_product_id")
	private Product product;

	@Column(name = "sale_price", precision = 10, scale = 2)
	private BigDecimal salePrice;
	
	@Column(name = "quantity")
	private Integer quantity; // Số lượng sản phẩm trả lại

	@Column(name = "refund_amount", precision = 10, scale = 2)
	private BigDecimal refundAmount; // Số tiền hoàn lại cho mặt hàng này

}

package com.multishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "return_policies")
public class ReturnPolicy extends Base {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop; // Liên kết đến cửa hàng

	@Column(name = "policy_name")
	private String policyName; // Tên chính sách

	@Column(name = "request_start_days")
	private int requestStartDays; // Số ngày bắt đầu yêu cầu trả hàng

	@Column(name = "request_end_days")
	private int requestEndDays; // Số ngày kết thúc yêu cầu trả hàng

	@Column(name = "policy_description", length = 500) // Thêm cột mô tả chính sách
	private String policyDescription; // Mô tả chi tiết về chính sách
}

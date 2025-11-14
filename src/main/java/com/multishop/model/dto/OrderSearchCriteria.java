package com.multishop.model.dto;

import java.time.LocalDateTime;

import com.multishop.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderSearchCriteria {

  private String keySearch;      // tìm theo tên
  private OrderStatus orderStatus;
  private LocalDateTime orderDateFrom; // Ngày bắt đầu
  private LocalDateTime orderDateTo; // Ngày kết thúc

  // paging & sort
  private Integer pageNo = 0;
  private Integer pageSize = 10;
  private String sortBy = "id";  // field sort
  private String sortDir = "asc"; // asc | desc

}

package com.multishop.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductSearchCriteria {

  private String keySearch;
  private Byte status;
  private BigDecimal salePriceFrom;
  private BigDecimal salePriceTo;
  private BigDecimal rating;
  private List<Long> categoryIds;
  private List<Long> brandIds;

  // paging & sort
  private Integer pageNo = 0;
  private Integer pageSize = 10;
  private String sortBy = "id";  // field sort
  private String sortDir = "asc"; // asc | desc
}

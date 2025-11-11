package com.multishop.model.request;

import lombok.Data;

@Data
public class BrandSearchRequest {

  private Integer pageNo = 1;
  private Integer pageSize = 10;
  private Integer status;
  private String keySearch;

}

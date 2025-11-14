package com.multishop.model.request;

import lombok.Data;

@Data
public class AddressRequest {

  private Long id;
  private String phoneNumber;
  private String addressDetail;
  private Boolean isDefault;
  private String addressType;
  private Integer provinceId;
  private Integer districtId;
  private Integer wardId;

}

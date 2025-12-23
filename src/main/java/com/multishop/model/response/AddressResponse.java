package com.multishop.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressResponse {

  private Long id;
  private String phoneNumber;
  private String addressDetail;
  private Boolean isDefault;
  private String addressType;
  private Integer provinceId;
  private Integer districtId;
  private Integer wardId;

}

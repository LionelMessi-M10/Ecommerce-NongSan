package com.multishop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

  private Long id;
  private String receiveName;
  private String phoneNumber;
  private String addressDetail;
  private Boolean isDefault;
  private String addressType;
  private String provinceName;
  private String districtName;
  private String wardName;

}

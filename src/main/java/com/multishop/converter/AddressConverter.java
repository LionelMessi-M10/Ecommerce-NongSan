package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Address;
import com.multishop.model.dto.AddressDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AddressConverter {
  
  private final ModelMapper modelMapper;

  public AddressDTO convertToDTO(Address address) {
    AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

    addressDTO.setAddressType(address.getAddressType().name());
    addressDTO.setProvinceName(address.getProvince().getProvinceName());
    addressDTO.setDistrictName(address.getDistrict().getDistrictName());
    addressDTO.setWardName(address.getWard().getWardName());

    return addressDTO;
  }

}

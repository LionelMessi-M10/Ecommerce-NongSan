package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Address;
import com.multishop.enums.AddressType;
import com.multishop.model.dto.AddressDTO;
import com.multishop.repository.DistrictRepository;
import com.multishop.repository.ProvinceRepository;
import com.multishop.repository.WardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AddressConverter {
  
  private final ModelMapper modelMapper;
  private final ProvinceRepository provinceRepository;
  private final DistrictRepository districtRepository;
  private final WardRepository wardRepository;

  public AddressDTO convertToDTO(Address address) {
    AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);

    addressDTO.setAddressType(address.getAddressType().name());
    addressDTO.setProvinceId(address.getProvince().getProvinceId());
    addressDTO.setDistrictId(address.getDistrict().getDistrictId());
    addressDTO.setWardId(address.getWard().getWardCode());

    return addressDTO;
  }

  public Address convertToEntity(AddressDTO addressDTO) {
    Address address = modelMapper.map(addressDTO, Address.class);

    if(addressDTO.getAddressType() != null) address.setAddressType(AddressType.valueOf(addressDTO.getAddressType()));
    if(addressDTO.getProvinceId() != null) address.setProvince(provinceRepository.findByProvinceId(addressDTO.getProvinceId()).orElseThrow(() -> new RuntimeException("Not found province by id " + addressDTO.getProvinceId())));
    if(addressDTO.getDistrictId() != null) address.setDistrict(districtRepository.findByDistrictId(addressDTO.getDistrictId()).orElseThrow(() -> new RuntimeException("Not found district by id " + addressDTO.getDistrictId())));
    if(addressDTO.getWardId() != null) address.setWard(wardRepository.findByWardCode(addressDTO.getWardId()).orElseThrow(() -> new RuntimeException("Not found ward by id " + addressDTO.getWardId())));

    return address;
  }

}

package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Address;
import com.multishop.enums.AddressType;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.request.AddressRequest;
import com.multishop.model.response.AddressResponse;
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

  public AddressResponse convertToResponse(Address address) {
    AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);

    addressResponse.setAddressType(address.getAddressType().name());
    addressResponse.setProvinceId(address.getProvince().getProvinceId());
    addressResponse.setDistrictId(address.getDistrict().getDistrictId());
    addressResponse.setWardId(address.getWard().getWardCode());

    return addressResponse;
  }

  public Address convertToEntity(AddressRequest addressRequest) {
    Address address = modelMapper.map(addressRequest, Address.class);

    if (addressRequest.getAddressType() != null)
      address.setAddressType(AddressType.valueOf(addressRequest.getAddressType()));
    if (addressRequest.getProvinceId() != null)
      address.setProvince(provinceRepository.findByProvinceId(addressRequest.getProvinceId())
          .orElseThrow(
              () -> new ResourceNotFoundException("Not found province by id " + addressRequest.getProvinceId())));
    if (addressRequest.getDistrictId() != null)
      address.setDistrict(districtRepository.findByDistrictId(addressRequest.getDistrictId())
          .orElseThrow(
              () -> new ResourceNotFoundException("Not found district by id " + addressRequest.getDistrictId())));
    if (addressRequest.getWardId() != null)
      address.setWard(wardRepository.findByWardCode(addressRequest.getWardId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found ward by id " + addressRequest.getWardId())));

    return address;
  }

}

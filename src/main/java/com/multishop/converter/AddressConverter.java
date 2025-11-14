package com.multishop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.multishop.entity.Address;
import com.multishop.enums.AddressType;
import com.multishop.exception.ResourceNotFoundException;
import com.multishop.model.dto.AddressDTO;
import com.multishop.model.request.AddressRequest;
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

    if (addressDTO.getAddressType() != null)
      address.setAddressType(AddressType.valueOf(addressDTO.getAddressType()));
    if (addressDTO.getProvinceId() != null)
      address.setProvince(provinceRepository.findByProvinceId(addressDTO.getProvinceId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found province by id " + addressDTO.getProvinceId())));
    if (addressDTO.getDistrictId() != null)
      address.setDistrict(districtRepository.findByDistrictId(addressDTO.getDistrictId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found district by id " + addressDTO.getDistrictId())));
    if (addressDTO.getWardId() != null)
      address.setWard(wardRepository.findByWardCode(addressDTO.getWardId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found ward by id " + addressDTO.getWardId())));

    return address;
  }

  public Address convertToEntity(AddressRequest addressRequest) {
    Address address = modelMapper.map(addressRequest, Address.class);

    if (addressRequest.getAddressType() != null)
      address.setAddressType(AddressType.valueOf(addressRequest.getAddressType()));
    if (addressRequest.getProvinceId() != null)
      address.setProvince(provinceRepository.findByProvinceId(addressRequest.getProvinceId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found province by id " + addressRequest.getProvinceId())));
    if (addressRequest.getDistrictId() != null)
      address.setDistrict(districtRepository.findByDistrictId(addressRequest.getDistrictId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found district by id " + addressRequest.getDistrictId())));
    if (addressRequest.getWardId() != null)
      address.setWard(wardRepository.findByWardCode(addressRequest.getWardId())
          .orElseThrow(() -> new ResourceNotFoundException("Not found ward by id " + addressRequest.getWardId())));

    return address;
  }

}

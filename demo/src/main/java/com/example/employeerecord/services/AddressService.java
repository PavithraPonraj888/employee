package com.example.employeerecord.services;

import com.example.employeerecord.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto addAddress(AddressDto addressDto);
    List<AddressDto> getAllAddresses();
    AddressDto getAddressById(Long addressId);
    AddressDto updateAddress(Long addressId, AddressDto addressDto);
    void deleteAddress(Long addressId);
}


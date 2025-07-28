package com.example.employeerecord.services;

import com.example.employeerecord.dao.Address;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.AddressDto;
import com.example.employeerecord.mapper.AddressMapper;
import com.example.employeerecord.repository.AddressRepo;
import com.example.employeerecord.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        Employees employee = employeeRepo.findById(addressDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        address.setEmployee(employee);
        return addressMapper.toDto(addressRepo.save(address));
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return addressRepo.findAll()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDto updateAddress(Long addressId, AddressDto addressDto) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        return addressMapper.toDto(addressRepo.save(address));
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepo.delete(address);
    }
}

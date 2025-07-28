package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Address;
import com.example.employeerecord.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDto toDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setAddressId(address.getAddressId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        if (address.getEmployee() != null) {
            dto.setEmployeeId(address.getEmployee().getEmpId());
        }
        return dto;
    }

    public Address toEntity(AddressDto dto) {
        Address address = new Address();
        address.setAddressId(dto.getAddressId());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        return address;
    }
}


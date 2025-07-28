package com.example.employeerecord.controllers;

import com.example.employeerecord.dto.AddressDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.dto.SalaryInfoDto;
import com.example.employeerecord.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Create a new address
    @PostMapping("/add")
    public GenericResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto) {
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address added successfully")
                .data(addressService.addAddress(addressDto))
                .success(true)
                .build();
    }

    // Get all addresses
    @GetMapping("/all")
    public GenericResponseEntity<List<AddressDto>> getAllAddresses() {
        return GenericResponseEntity.<List<AddressDto>>builder()
                .message("Addresses fetched successfully")
                .data(addressService.getAllAddresses())
                .success(true)
                .build();

    }

    // Get address by ID
    @GetMapping("/{addressId}")
    public GenericResponseEntity<AddressDto> getAddressById(@PathVariable Long addressId) {
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address fetched successfully")
                .data(addressService.getAddressById(addressId))
                .success(true)
                .build();
    }

    // Update address by ID
    @PutMapping("/update/{addressId}")
    public GenericResponseEntity<AddressDto> updateAddress(@PathVariable Long addressId, @RequestBody AddressDto addressDto) {
        return GenericResponseEntity.<AddressDto>builder()
                .message("Address updated successfully")
                .data(addressService.updateAddress(addressId, addressDto))
                .success(true)
                .build();

    }

    // Delete address by ID
    @DeleteMapping("/delete/{addressId}")
    public GenericResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return GenericResponseEntity.<String>builder()
                .message("Address deleted successfully")
                .data(null)
                .success(true)
                .build();
    }
}


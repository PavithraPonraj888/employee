package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}


package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees,Long> {

    Optional<Employees> findByEmail(String email);


}
package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employees,Long> {
    boolean existsByEmail(String email);

}

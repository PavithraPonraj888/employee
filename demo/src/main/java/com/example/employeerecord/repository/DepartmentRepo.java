package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {

}

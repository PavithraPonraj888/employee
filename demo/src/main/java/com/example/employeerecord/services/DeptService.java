package com.example.employeerecord.services;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.DepartmentDto;
import com.example.employeerecord.dto.EmployeeDto;

import java.util.List;

public interface DeptService {
    DepartmentDto addDept(Department department);
    List<Department> getDept();
    DepartmentDto updateDept(Long deptId, Department updatedDept);
    String deleteEmployee(Long id);


}

package com.example.employeerecord.services;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDept(Department department);
    List<Department> getDept();
    DepartmentDto updateDept(Long deptId, Department updatedDept);
    String deleteEmployee(Long id);


}

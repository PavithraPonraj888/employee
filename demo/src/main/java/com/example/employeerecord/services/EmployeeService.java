package com.example.employeerecord.services;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;

import java.util.List;

    public interface EmployeeService {


        EmployeeDto createEmployee(EmployeeDto employee);


        EmployeeDto getEmployeeById(Long empId);

        EmployeeDto updateEmployee(Long empId, Employees updatedEmployee);

        String deleteEmployee(Long empId);
        List<EmployeeDto> fetchAllEmployee();


        EmployeeDto assignProject(Long empId, Long projId);
    }





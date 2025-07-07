package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;

import java.util.List;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employees employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setEmp_id(employee.getEmp_id());
        return dto;
    }
    public static List<EmployeeDto> EmployeesToEmployeeDtoList(List<Employees> employeesList) {
        return employeesList.stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    public static Employees toEntity(EmployeeDto dto) {
        Employees employee = new Employees();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setEmp_id(dto.getEmp_id());
        // Password is not included in DTO for security
        return employee;
    }
}


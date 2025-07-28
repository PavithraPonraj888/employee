/*package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employees employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setEmp_id(employee.getEmpId());
        dto.setDepartment(employee.getDepartment());
        dto.setUserProfile(employee.getUserProfile());
        dto.setProjects(employee.getProjects());
        employee.setPassword(dto.getPassword()); // now dto has password

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
        employee.setPassword(dto.getPassword());
        employee.setDepartment(dto.getDepartment());
        employee.setUserProfile(dto.getUserProfile());
        employee.setPhone(dto.getPhone());
        employee.setEmpId(dto.getEmp_id());
        return employee;
    }
}*/
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
        dto.setEmp_id(employee.getEmpId());
        dto.setDepartment(employee.getDepartment());
        dto.setUserProfile(employee.getUserProfile());
        dto.setProjects(employee.getProjects());

        // ❌ DO NOT overwrite employee password here!
        // dto.setPassword(employee.getPassword()); // Optional, only if you need it in DTO (not recommended)

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
        employee.setPassword(dto.getPassword()); // assumed already hashed in service
        employee.setDepartment(dto.getDepartment());
        employee.setUserProfile(dto.getUserProfile());
        employee.setPhone(dto.getPhone());
        employee.setEmpId(dto.getEmp_id());
        return employee;
    }
}


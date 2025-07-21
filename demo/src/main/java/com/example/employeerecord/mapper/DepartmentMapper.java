package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dto.DepartmentDto;

public class DepartmentMapper {
    public static DepartmentDto toDepartmentDto(Department department){
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDeptId(department.getDept_id());
        departmentDto.setDeptName(department.getDept_name());
        return departmentDto;
    }
}
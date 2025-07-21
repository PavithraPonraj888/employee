package com.example.employeerecord.dto;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDto {
    private String name;
    private String email;
    private String phone;
    private Long emp_id;
    private Department department;
    private UserProfile userProfile;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;




}

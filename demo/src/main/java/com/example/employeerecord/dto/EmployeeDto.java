package com.example.employeerecord.dto;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dao.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {
    private String name;
    private String email;
    private String phone;
    private Long emp_id;
    private Department department;
    private UserProfile userProfile;
    //private Project project;
    private List<Project> projects;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;




}

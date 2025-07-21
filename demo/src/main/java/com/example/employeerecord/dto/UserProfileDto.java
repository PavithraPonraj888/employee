package com.example.employeerecord.dto;

import com.example.employeerecord.dao.Employees;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {
    private Long profileId;
    private String gender;
    private int age;
    private LocalDate joinDate;
    private String location;
    private Long empId;
}

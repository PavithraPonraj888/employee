package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.dto.UserProfileDto;

public class UserProfileMapper {
    public static UserProfileDto toDto(UserProfile userProfile) {
        UserProfileDto dto = new UserProfileDto();
        dto.setProfileId(userProfile.getProfileId());
        dto.setGender(userProfile.getGender());
        dto.setAge(userProfile.getAge());
        dto.setJoinDate(userProfile.getJoinDate());
        dto.setLocation(userProfile.getLocation());
        dto.setEmpId(userProfile.getEmployee().getEmpId());
        return dto;
    }
}

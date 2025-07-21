package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepo extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findByEmployeeEmpId(Long emp_id);


}

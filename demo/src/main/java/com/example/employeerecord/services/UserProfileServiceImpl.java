
package com.example.employeerecord.services;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.Exception.EmloyeeNotFoundException;
import com.example.employeerecord.Exception.UserProfileNotFoundException;
import com.example.employeerecord.dto.UserProfileDto;
import com.example.employeerecord.mapper.UserProfileMapper;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class UserProfileServiceImpl implements UserProfileService {

        @Autowired
        private UserProfileRepo profileRepo;

        @Autowired
        private EmployeeRepo employeeRepo;

        @Override
        public UserProfileDto getProfileByEmpId(Long emp_id) {
            UserProfile user=profileRepo.findByEmployeeEmpId(emp_id)
                    .orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found for employee ID: " + emp_id));
            return UserProfileMapper.toDto(user);
        }


    @Override
    public UserProfile createProfile(Long empId, UserProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile must not be null.");
        }

        // Check if employee exists
        Employees employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new EmloyeeNotFoundException("Employee not found with ID: " + empId));

        // Check if profile already exists
        if (profileRepo.findByEmployeeEmpId(empId).isPresent()) {
            throw new IllegalStateException("Profile already exists for employee ID: " + empId);
        }

        // Set the employee (this is enough for @MapsId to set the FK)
        profile.setEmployee(employee);
        employee.setUserProfile(profile); // optional but good for bidirectional sync

        return profileRepo.save(profile);
    }



    public UserProfileDto updateProfile(Long emp_id, UserProfile updatedProfile) {
            UserProfile existing = profileRepo.findByEmployeeEmpId(emp_id)
                    .orElseThrow(() -> new UserProfileNotFoundException("Profile not found for emp ID: " + emp_id));

            existing.setGender(updatedProfile.getGender());
            existing.setAge(updatedProfile.getAge());
            existing.setJoinDate(updatedProfile.getJoinDate());
            existing.setLocation(updatedProfile.getLocation());
            UserProfile save=profileRepo.save(existing);
            return UserProfileMapper.toDto(save);
        }

        public void deleteProfile(Long emp_id) {
            UserProfile profile = profileRepo.findByEmployeeEmpId(emp_id)
                    .orElseThrow(() -> new UserProfileNotFoundException("Profile not found for emp ID: " + emp_id));
            profileRepo.delete(profile);
        }

        public List<UserProfile> getAllProfiles() {
            return profileRepo.findAll();
        }

    }



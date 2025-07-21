
package com.example.employeerecord.services;

import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.dto.UserProfileDto;

import java.util.List;

public interface UserProfileService {


    UserProfileDto getProfileByEmpId(Long emp_id);
    UserProfileDto updateProfile(Long emp_id, UserProfile updatedProfile);
    void deleteProfile(Long emp_id);
    List<UserProfile> getAllProfiles();

    UserProfile createProfile(Long emp_id,UserProfile profile);
}

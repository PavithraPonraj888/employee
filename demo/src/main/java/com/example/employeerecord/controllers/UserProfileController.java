
package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.dto.DepartmentDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.dto.UserProfileDto;
import com.example.employeerecord.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api")
public class UserProfileController {
    private final String USERPROFILE_ADDED_SUCCESSFULLY="UserProfile Added successfully";
    private final String USERPROFILE_FETCHED_SUCCESSFULLY="UserProfile fetched successfully";
    private final String USERPROFILES_FETCHED_SUCCESSFULLY="UserProfiles fetched successfully";
    private final String USERPROFILE_UPDATED_SUCCESSFULLY="UserProfile updated successfully";
    private final String USERPROFILE_DELETED_SUCCESSFULLY="UserProfile deleted successfully";
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/addProfile/{empId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<UserProfile> createProfile(@PathVariable Long empId, @RequestBody UserProfile profile) {
        return GenericResponseEntity.<UserProfile>builder()
                .message(USERPROFILE_ADDED_SUCCESSFULLY)
                .data(userProfileService.createProfile(empId,profile))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

    }


    @GetMapping("/getProfileByEmpId/{empId}")
    public GenericResponseEntity<UserProfileDto> getByEmpId(@PathVariable Long empId) {
        return GenericResponseEntity.<UserProfileDto>builder()
                .message(USERPROFILE_FETCHED_SUCCESSFULLY)
                .data(userProfileService.getProfileByEmpId(empId))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }


    @PutMapping(value = "/updateProfile/{empId}", consumes = "application/json")
    public GenericResponseEntity<UserProfileDto> updateProfile(@PathVariable Long empId, @RequestBody UserProfile updatedProfile) {
        return GenericResponseEntity.<UserProfileDto>builder()
                .message(USERPROFILE_UPDATED_SUCCESSFULLY)
                .data(userProfileService.updateProfile(empId, updatedProfile))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }


    @DeleteMapping("/deleteProfile/{empId}")
    public GenericResponseEntity<String> deleteProfile(@PathVariable Long empId) {
        userProfileService.deleteProfile(empId);
        return GenericResponseEntity.<String>builder()
                .message(USERPROFILE_DELETED_SUCCESSFULLY)
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }

    @GetMapping("/getAllProfiles")
    public List<UserProfile> getAllProfiles() {
        return GenericResponseEntity.<List<UserProfile>>builder()
                .message(USERPROFILES_FETCHED_SUCCESSFULLY)
                .data(userProfileService.getAllProfiles())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
}


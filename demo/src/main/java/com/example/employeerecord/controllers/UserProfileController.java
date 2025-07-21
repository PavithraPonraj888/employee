
package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.dto.UserProfileDto;
import com.example.employeerecord.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/addProfile/{empId}")
    public UserProfile createProfile(@PathVariable Long empId,@RequestBody UserProfile profile) {
        return userProfileService.createProfile(empId,profile);
    }


    @GetMapping("/getProfileByEmpId/{empId}")
    public ResponseEntity<UserProfileDto> getByEmpId(@PathVariable Long empId) {
        return ResponseEntity.ok(userProfileService.getProfileByEmpId(empId));
    }


    @PutMapping(value = "/updateProfile/{empId}", consumes = "application/json")
    public ResponseEntity<UserProfileDto> updateProfile(@PathVariable Long empId, @RequestBody UserProfile updatedProfile) {
        return ResponseEntity.ok(userProfileService.updateProfile(empId, updatedProfile));
    }


    @DeleteMapping("/deleteProfile/{empId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long empId) {
        userProfileService.deleteProfile(empId);
        return ResponseEntity.ok("User profile deleted successfully.");
    }

    @GetMapping("/getAllProfiles")
    public List<UserProfile> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }
}


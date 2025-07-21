package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.UserProfile;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.services.EmpService;
import com.example.employeerecord.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @Autowired
    private UserProfileService userProfileService;
    @PostMapping("/user-profiles/{empId}")
    public ResponseEntity<UserProfile> createProfile(@PathVariable Long empId,
                                                     @RequestBody UserProfile profileData) {
        UserProfile created = userProfileService.createProfile(empId, profileData);
        return ResponseEntity.ok(created);
    }


    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployeee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = empService.CreateEmployee(employeeDto);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping("/fetchbyId/{emp_id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long emp_id) {
        EmployeeDto employee = empService.getEmployeeById(emp_id);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/fetchAll/{emp_id}")
    public List<EmployeeDto> get() {
        List<EmployeeDto> employee=empService.fetchAllEmployee();
        return employee;
    }

    @PutMapping("/update/{emp_id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long emp_id, @RequestBody Employees updatedData) {
        EmployeeDto updated = empService.updateEmployee(emp_id, updatedData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{emp_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long emp_id) {
        empService.deleteEmployee(emp_id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
    @PutMapping("/assignProject/{projId}/toEmployee/{empId}")
    public EmployeeDto assignProject(@PathVariable long empId,@PathVariable long projId){
        return empService.assignProject(empId,projId);
    }
}

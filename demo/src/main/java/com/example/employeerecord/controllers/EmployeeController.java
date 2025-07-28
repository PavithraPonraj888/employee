package com.example.employeerecord.controllers;

import com.example.employeerecord.Security.JwtUtil;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.services.EmployeeService;
import com.example.employeerecord.services.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final String EMPLOYEE_ADDED_SUCCESSFULLY="Employee Added successfully";
    private final String EMPLOYEE_FETCHED_SUCCESSFULLY="Employee fetched successfully";
    private final String EMPLOYEES_FETCHED_SUCCESSFULLY="Employees fetched successfully";
    private final String EMPLOYEE_UPDATED_SUCCESSFULLY="Employee updated successfully";
    private final String EMPLOYEE_DELETED_SUCCESSFULLY="Employee deleted successfully";
    private final String PROJECT_ASSIGNED_SUCCESSFULLY="Project Assigned Successfully";
    @Autowired
    private EmployeeService empService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepo empRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<EmployeeDto> createEmployeee(@RequestBody EmployeeDto employeeDto) {
        //employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        EmployeeDto createdEmployee = empService.createEmployee(employeeDto);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message(EMPLOYEE_ADDED_SUCCESSFULLY)
                .data(createdEmployee)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }

    /*@GetMapping("/fetchbyId/{emp_id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long emp_id) {
        EmployeeDto employee = empService.getEmployeeById(emp_id);
        return ResponseEntity.ok(employee);
    }*/


    @GetMapping("/Id")
    public GenericResponseEntity<EmployeeDto> getEmployee(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtil.extractEmployeeId(token);
        EmployeeDto emp=empService.getEmployeeById(id);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message(EMPLOYEE_FETCHED_SUCCESSFULLY)
                .data(emp)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }




   /* @PutMapping("/hashPassword")
    public GenericResponseEntity<List<Employees>> hashPassword() {
        List<Employees> employeesList = empRepo.findAll();
        for (Employees employee : employeesList) {
            String currentPassword = employee.getPassword();
            if (currentPassword != null && !currentPassword.isBlank()) {
                employee.setPassword(passwordEncoder.encode(currentPassword));
                empRepo.save(employee); // Don't forget to persist changes
            }
        }
        return GenericResponseEntity.<List<Employees>>builder()
                .message("Password hashed successfully")
                .data(employeesList)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }*/


    @GetMapping
    public GenericResponseEntity<List<EmployeeDto>> get() {
        List<EmployeeDto> employee=empService.fetchAllEmployee();
        return GenericResponseEntity.<List<EmployeeDto>>builder()
                .message(EMPLOYEES_FETCHED_SUCCESSFULLY)
                .data(employee)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @PutMapping
    public GenericResponseEntity<EmployeeDto> updateEmployee( @RequestBody Employees updatedData ,HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtil.extractEmployeeId(token);
        EmployeeDto updated = empService.updateEmployee(id, updatedData);
        return GenericResponseEntity.<EmployeeDto>builder()
                .message(EMPLOYEE_UPDATED_SUCCESSFULLY)
                .data(updated)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @DeleteMapping
    public GenericResponseEntity<String> deleteEmployee(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long id=jwtUtil.extractEmployeeId(token);
        empService.deleteEmployee(id);
        return GenericResponseEntity.<String>builder()
                .message(EMPLOYEE_DELETED_SUCCESSFULLY)
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }
    /*@PutMapping("/assignProject/{projId}/toEmployee/{empId}")
    public EmployeeDto assignProject(@PathVariable long empId,@PathVariable long projId){
        return empService.assignProject(empId,projId);
    }*/
    @PutMapping("/{projId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<EmployeeDto> assignProject(HttpServletRequest request, @PathVariable long projId){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        long empId=jwtUtil.extractEmployeeId(token);

        return GenericResponseEntity.<EmployeeDto>builder()
                .message(PROJECT_ASSIGNED_SUCCESSFULLY)
                .data(empService.assignProject(empId,projId))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }
}

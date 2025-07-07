package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmpService empService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> add(@RequestBody Employees e) {
        EmployeeDto created = empService.CreateEmployee(e);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/fetchbyId/{emp_id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long emp_id) {
        EmployeeDto employee = empService.getEmployeeById(emp_id);
        return ResponseEntity.ok(employee);
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
}

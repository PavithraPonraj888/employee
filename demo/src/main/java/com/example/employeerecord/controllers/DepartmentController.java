package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.DepartmentDto;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.repository.DepartmentRepo;
import com.example.employeerecord.services.DeptService;
import com.example.employeerecord.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DeptService deptService;

    @PostMapping("/adding")
    public DepartmentDto add(@RequestBody Department department) {
        return deptService.addDept(department);
    }
    @GetMapping("/getAll")
    public List<Department> get() {
        return deptService.getDept();
    }
    @PutMapping("/updateDept/{dept_id}")
    public DepartmentDto update(@PathVariable("dept_id") Long id,@RequestBody Department updatedData){
       return deptService.updateDept(id,updatedData);
    }
    @DeleteMapping("/deleting/{dept_id}")
    public String delete(@PathVariable Long dept_id){
        deptService.deleteEmployee(dept_id);
        return "department deleted successfully";

    }



}
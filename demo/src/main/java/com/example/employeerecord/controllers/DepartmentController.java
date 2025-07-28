package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dto.DepartmentDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.repository.DepartmentRepo;
import com.example.employeerecord.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final String DEPARTMENT_ADDED_SUCCESSFULLY="Department Added successfully";
    private final String DEPARTMENTS_FETCHED_SUCCESSFULLY="Departments fetched successfully";
    private final String DEPARTMENT_UPDATED_SUCCESSFULLY="Department updated successfully";
    private final String DEPARTMENT_DELETED_SUCCESSFULLY="Department deleted successfully";

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentService deptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseEntity<DepartmentDto> add(@RequestBody Department department) {
        DepartmentDto dept=deptService.addDept(department);
        return GenericResponseEntity.<DepartmentDto>builder()
                .message(DEPARTMENT_ADDED_SUCCESSFULLY)
                .data(dept)
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }
    @GetMapping
    public GenericResponseEntity<List<Department>> get() {
        return GenericResponseEntity.<List<Department>>builder()
                .message(DEPARTMENTS_FETCHED_SUCCESSFULLY)
                .data(deptService.getDept())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }
    @PutMapping("/{dept_id}")
    public GenericResponseEntity<DepartmentDto> update(@PathVariable("dept_id") Long id,@RequestBody Department updatedData){
        return GenericResponseEntity.<DepartmentDto>builder()
                .message(DEPARTMENT_UPDATED_SUCCESSFULLY)
                .data(deptService.updateDept(id,updatedData))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();

    }
    @DeleteMapping("/{dept_id}")
    public GenericResponseEntity<String> delete(@PathVariable Long dept_id){
        deptService.deleteEmployee(dept_id);
        return GenericResponseEntity.<String>builder()
                .message(DEPARTMENT_DELETED_SUCCESSFULLY)
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();



    }



}
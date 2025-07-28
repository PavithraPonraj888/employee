package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.dto.SalaryInfoDto;
import com.example.employeerecord.services.SalaryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryInfoController {

    @Autowired
    private SalaryInfoService salaryInfoService;

    @PostMapping("/add")
    public GenericResponseEntity<SalaryInfoDto> addSalary(@RequestBody SalaryInfoDto dto) {
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary added successfully")
                .data(salaryInfoService.addSalaryInfo(dto))
                .success(true)
                .build();
    }

    @GetMapping("/{salaryId}")
    public GenericResponseEntity<SalaryInfoDto> getSalary(@PathVariable Long salaryId) {
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary fetched successfully")
                .data(salaryInfoService.getSalaryInfoById(salaryId))
                .success(true)
                .build();
    }

    @PutMapping("/update/{salaryId}")
    public GenericResponseEntity<SalaryInfoDto> updateSalary(@PathVariable Long salaryId, @RequestBody SalaryInfoDto dto) {
        return GenericResponseEntity.<SalaryInfoDto>builder()
                .message("Salary updated successfully")
                .data(salaryInfoService.updateSalaryInfo(salaryId, dto))
                .success(true)
                .build();
    }

    @DeleteMapping("/delete/{salaryId}")
    public GenericResponseEntity<String> deleteSalary(@PathVariable Long salaryId) {
        salaryInfoService.deleteSalaryInfo(salaryId);
        return GenericResponseEntity.<String>builder()
                .message("Salary deleted successfully")
                .data(null)
                .success(true)
                .build();
    }
}


package com.example.employeerecord.dto;

import lombok.Data;

@Data
public class SalaryInfoDto {
    private Long salaryId;

    private Double basic;
    private Double bonus;
    private Double deductions;

    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branch;

    private Long employeeId;  // Reference to the employee
}


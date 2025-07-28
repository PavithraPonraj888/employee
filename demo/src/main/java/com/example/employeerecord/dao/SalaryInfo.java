package com.example.employeerecord.dao;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "salaryId")
public class SalaryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;
    private Double basic;
    private Double bonus;
    private Double deductions;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branch;

    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employees employee;

}


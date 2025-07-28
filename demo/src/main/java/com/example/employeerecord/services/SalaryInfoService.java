package com.example.employeerecord.services;

import com.example.employeerecord.dto.SalaryInfoDto;

public interface SalaryInfoService {
    SalaryInfoDto addSalaryInfo(SalaryInfoDto salaryInfoDto);
    SalaryInfoDto getSalaryInfoById(Long salaryId);
    SalaryInfoDto updateSalaryInfo(Long salaryId, SalaryInfoDto salaryInfoDto);
    void deleteSalaryInfo(Long salaryId);
}


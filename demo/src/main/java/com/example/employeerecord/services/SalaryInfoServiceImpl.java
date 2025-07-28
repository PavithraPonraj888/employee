package com.example.employeerecord.services;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.SalaryInfo;
import com.example.employeerecord.dto.SalaryInfoDto;
import com.example.employeerecord.mapper.SalaryInfoMapper;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.repository.SalaryInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryInfoServiceImpl implements SalaryInfoService {

    @Autowired
    private SalaryInfoRepo salaryInfoRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SalaryInfoMapper salaryInfoMapper;

    @Override
    public SalaryInfoDto addSalaryInfo(SalaryInfoDto dto) {
        SalaryInfo salaryInfo = salaryInfoMapper.toEntity(dto);
        Employees employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        salaryInfo.setEmployee(employee);
        return salaryInfoMapper.toDto(salaryInfoRepo.save(salaryInfo));
    }

    @Override
    public SalaryInfoDto getSalaryInfoById(Long salaryId) {
        SalaryInfo salaryInfo = salaryInfoRepo.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary info not found"));
        return salaryInfoMapper.toDto(salaryInfo);
    }

    @Override
    public SalaryInfoDto updateSalaryInfo(Long salaryId, SalaryInfoDto dto) {
        SalaryInfo salaryInfo = salaryInfoRepo.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary info not found"));

        salaryInfo.setBasic(dto.getBasic());
        salaryInfo.setBonus(dto.getBonus());
        salaryInfo.setDeductions(dto.getDeductions());
        salaryInfo.setBankName(dto.getBankName());
        salaryInfo.setAccountNumber(dto.getAccountNumber());
        salaryInfo.setIfscCode(dto.getIfscCode());
        salaryInfo.setBranch(dto.getBranch());

        return salaryInfoMapper.toDto(salaryInfoRepo.save(salaryInfo));
    }

    @Override
    public void deleteSalaryInfo(Long salaryId) {
        salaryInfoRepo.deleteById(salaryId);
    }
}


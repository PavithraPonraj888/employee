package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.SalaryInfo;
import com.example.employeerecord.dto.SalaryInfoDto;
import org.springframework.stereotype.Component;

@Component
public class SalaryInfoMapper {

    public SalaryInfoDto toDto(SalaryInfo entity) {
        SalaryInfoDto dto = new SalaryInfoDto();
        dto.setSalaryId(entity.getSalaryId());
        dto.setBasic(entity.getBasic());
        dto.setBonus(entity.getBonus());
        dto.setDeductions(entity.getDeductions());
        dto.setBankName(entity.getBankName());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setIfscCode(entity.getIfscCode());
        dto.setBranch(entity.getBranch());
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getEmpId());
        }
        return dto;
    }

    public SalaryInfo toEntity(SalaryInfoDto dto) {
        SalaryInfo entity = new SalaryInfo();
        entity.setSalaryId(dto.getSalaryId());
        entity.setBasic(dto.getBasic());
        entity.setBonus(dto.getBonus());
        entity.setDeductions(dto.getDeductions());
        entity.setBankName(dto.getBankName());
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setIfscCode(dto.getIfscCode());
        entity.setBranch(dto.getBranch());
        return entity;
    }
}

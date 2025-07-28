package com.example.employeerecord.repository;

import com.example.employeerecord.dao.SalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryInfoRepo extends JpaRepository<SalaryInfo, Long> {
}


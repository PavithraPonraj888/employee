package com.example.employeerecord.repository;

import com.example.employeerecord.dao.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    boolean existsByProjId(Long id);
}
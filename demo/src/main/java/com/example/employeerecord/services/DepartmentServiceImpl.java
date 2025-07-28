package com.example.employeerecord.services;

import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dto.DepartmentDto;
import com.example.employeerecord.mapper.DepartmentMapper;
import com.example.employeerecord.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo deptRepo;
    @Override
    public DepartmentDto addDept(Department department){
        Department dept=deptRepo.save(department);
        return DepartmentMapper.toDepartmentDto(dept);

    }
    public List<Department> getDept() {
        return deptRepo.findAll();
    }
    public DepartmentDto updateDept(Long deptId, Department updatedDept){
        Department existingData=deptRepo.findById(deptId).orElse(null);
        existingData.setDept_name(updatedDept.getDept_name());
        Department dept=deptRepo.save(existingData);
        return DepartmentMapper.toDepartmentDto(dept);
    }
    public String deleteEmployee(Long id){
        deptRepo.deleteById(id);
        return "department deleted successfully";
    }

}

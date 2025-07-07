package com.example.employeerecord.services;

import com.example.employeerecord.Exception.EmloyeeNotFoundException;
import com.example.employeerecord.Exception.InvalidDataException;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.mapper.EmployeeMapper;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.employeerecord.dataUtility.DataValidation.validateData;

@Service
public class EmpServiceI implements EmpService {
    @Autowired
    private EmployeeRepo emprepo;
    @Override
    public EmployeeDto CreateEmployee(Employees employee) {
        String error= validateData(employee,emprepo,null);
        if(!error.isEmpty())
            throw new InvalidDataException(error);

        Employees emp=emprepo.save(employee);
        return EmployeeMapper.toDto(emp);
    }

    public List<EmployeeDto> addAllEmployees(List<Employees> employeesList){
        ArrayList<String> Errors=new ArrayList<>();
        for(int i=0;i<employeesList.size();i++){
            String error= validateData(employeesList.get(i),emprepo,null);
            if(!error.isEmpty())
                Errors.add("Error found in record"+i+error);
        }
        if(Errors.size()!=0){
            throw new InvalidDataException(Errors.toString());
        }
        emprepo.saveAll(employeesList);
        return EmployeeMapper.EmployeesToEmployeeDtoList(employeesList);
    }

    public EmployeeDto getEmployeeById(Long id){
        Employees emp=emprepo.findById(id).orElse(null);
        if(emp==null){
            throw new EmloyeeNotFoundException("No Employee with id:"+id+" found");
        }
        return EmployeeMapper.toDto(emp);
    }

    public EmployeeDto updateEmployee(Long id,Employees updatedData){
        Employees existingData=emprepo.findById(id).orElse(null);
        if(existingData!=null){
            String error= validateData(existingData,emprepo,String.valueOf(existingData.getEmp_id()));
            if(!error.isEmpty())
                throw new InvalidDataException(error);
            existingData.setName(updatedData.getName());
            existingData.setEmail(updatedData.getEmail());
            existingData.setPhone(updatedData.getPhone());
            existingData.setPassword(updatedData.getPassword());
            emprepo.save(existingData);
        }
        assert existingData != null;
        return EmployeeMapper.toDto(existingData);
    }

    public String deleteEmployee(Long id){
        emprepo.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public List<EmployeeDto> fetchAllEmployee() {
        List<Employees> employees = emprepo.findAll();
        return EmployeeMapper.EmployeesToEmployeeDtoList(employees);
    }
}


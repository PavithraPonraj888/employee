package com.example.employeerecord.services;

import com.example.employeerecord.Exception.DepartmentNotFoundException;
import com.example.employeerecord.Exception.EmloyeeNotFoundException;
import com.example.employeerecord.Exception.InvalidDataException;
import com.example.employeerecord.dao.Department;
import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.mapper.EmployeeMapper;
import com.example.employeerecord.repository.DepartmentRepo;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.repository.ProjectRepo;
import com.example.employeerecord.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.employeerecord.dataUtility.DataValidation.validateData;

@Service
public class EmpServiceI implements EmpService {
    @Autowired
    private EmployeeRepo emprepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileRepo profileRepo;
    @Autowired
    private ProjectRepo projectRepo;
    @Override
    public EmployeeDto CreateEmployee(EmployeeDto employee) {
        Department dept = null;

        if (employee.getDepartment() != null && employee.getDepartment().getDept_id() != 0) {
            dept = departmentRepo.findById(employee.getDepartment().getDept_id())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department does not exist with id: " + employee.getDepartment().getDept_id()));
        }

        employee.setDepartment(dept);
        String error = validateData(employee, emprepo, null);
        if (!error.isEmpty()) {
            throw new InvalidDataException(error);
        }
        Employees emp = emprepo.save(EmployeeMapper.toEntity(employee));
        if(employee.getUserProfile()!=null){
            userProfileService.createProfile(emp.getEmpId(), employee.getUserProfile());}
        return EmployeeMapper.toDto(emp);
    }



    public List<EmployeeDto> addAllEmployees(List<Employees> employeesList){
        ArrayList<String> Errors=new ArrayList<>();
        for(int i=0;i<employeesList.size();i++){
            String error= validateData(EmployeeMapper.toDto(employeesList.get(i)),emprepo,null);
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
    public EmployeeDto updateEmployee(Long id, Employees updatedData) {
        Employees existingData = emprepo.findById(id).orElse(null);
        if (existingData == null) {
            throw new EmloyeeNotFoundException("No employee with id: " + id + " found");
        }

        if (updatedData.getDepartment() != null && updatedData.getDepartment().getDept_id() != 0) {
            Department dept = departmentRepo.findById(updatedData.getDepartment().getDept_id())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department does not exist with id: " + updatedData.getDepartment().getDept_id()));
            existingData.setDepartment(dept);
        }

        existingData.setName(updatedData.getName());
        existingData.setEmail(updatedData.getEmail());
        existingData.setPhone(updatedData.getPhone());
        existingData.setPassword(updatedData.getPassword());

        String error = validateData(EmployeeMapper.toDto(existingData), emprepo, String.valueOf(existingData.getEmpId()));
        if (!error.isEmpty()) {
            throw new InvalidDataException(error);
        }

        Employees saved = emprepo.save(existingData);
        return EmployeeMapper.toDto(saved);
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

    @Override
    public EmployeeDto assignProject(Long empId, Long projId){
        Employees employee=emprepo.findById(empId).
                orElseThrow(()->new EmloyeeNotFoundException("Employee with id: "+empId+" not found"));
        Project project=projectRepo.findById(projId).orElseThrow(()->new com.example.employeerecord.exceptions.ResourceNotFoundException("Project with id: "+projId+" not found"));
        employee.getProjects().add(project);
        project.getEmployeesList().add(employee);
        projectRepo.save(project);
        emprepo.save(employee);
        return EmployeeMapper.toDto(employee);
    }
}


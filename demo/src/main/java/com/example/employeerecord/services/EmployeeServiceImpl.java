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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.employeerecord.dataUtility.DataValidation.validateData;

@Service
public class EmployeeServiceImpl implements EmployeeService {
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // 1. Validate and set department
        Department dept = null;
        if (employeeDto.getDepartment() != null && employeeDto.getDepartment().getDept_id() != 0) {
            dept = departmentRepo.findById(employeeDto.getDepartment().getDept_id())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department does not exist with id: " + employeeDto.getDepartment().getDept_id()));
        }
        employeeDto.setDepartment(dept);

        // 2. Validate input
        String error = validateData(employeeDto, emprepo, null);
        if (!error.isEmpty()) {
            throw new InvalidDataException(error);
        }

        // 3. Hash the password (forcefully, assume it's raw)
        String rawPassword = employeeDto.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        employeeDto.setPassword(hashedPassword);

        // 4. Save employee
        Employees emp = emprepo.save(EmployeeMapper.toEntity(employeeDto));

        // 5. Create user profile if present
        if (employeeDto.getUserProfile() != null) {
            userProfileService.createProfile(emp.getEmpId(), employeeDto.getUserProfile());
        }

        // 6. Return saved employee DTO
        return EmployeeMapper.toDto(emp);
    }




    /* public List<EmployeeDto> addAllEmployees(List<Employees> employeesList){
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
    }*/
   public List<EmployeeDto> addAllEmployees(List<Employees> employeesList) {
       for (Employees emp : employeesList) {
           // Always assume raw password and hash it
           System.out.println("Hashing password for: " + emp.getEmail());
           emp.setPassword(passwordEncoder.encode(emp.getPassword()));
       }

       List<Employees> saved = emprepo.saveAll(employeesList);
       return saved.stream()
               .map(EmployeeMapper::toDto)
               .collect(Collectors.toList());
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
        existingData.setPassword(passwordEncoder.encode(updatedData.getPassword()));


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


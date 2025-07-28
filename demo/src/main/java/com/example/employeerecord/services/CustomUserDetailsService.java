package com.example.employeerecord.services;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.mapper.EmployeeMapper;
import com.example.employeerecord.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Component

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeesRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employees employee=employeesRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User: "+username+" not found"));
        return new User(employee.getEmail(),employee.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USERROLE")));
    }
}
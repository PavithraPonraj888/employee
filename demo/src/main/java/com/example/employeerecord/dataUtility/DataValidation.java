package com.example.employeerecord.dataUtility;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.repository.EmployeeRepo;
import com.example.employeerecord.dao.Employees;

import java.util.regex.Pattern;

public class DataValidation {
    public static String validateData(Employees employee, EmployeeRepo emprepo,String id){
        String error="";
        String emailRegex="^[a-zA-Z0-9._+-]+@[a-zA-Z._]+\\.[a-zA-Z]{2,}";
        String phoneRegex="^[6-9][0-9]{9}$";

        error=employee.getName()==null || employee.getName().isEmpty() ?error+"Employee name is required\n": error;

        error=!Pattern.matches(emailRegex,employee.getEmail())?error+"Invalid Email address\n":error;
        error=employee.getEmail()==null?error+"Email is required\n":error;
        error=emprepo.existsByEmail(employee.getEmail())?"Email id already exists\n":error;

        error=employee.getPhone()==null?error+"Phone number is required\n":error;
        error=!Pattern.matches(phoneRegex,employee.getPhone())?error+"Invalid phone number\n":error;

        error= employee.getPassword().length()<8 ?error+"Password is required and must be of length 8\n":error;
        return error;
    }
}


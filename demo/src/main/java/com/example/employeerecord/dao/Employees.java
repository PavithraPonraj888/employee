package com.example.employeerecord.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
public class Employees {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long emp_id;
    private String name;
    private String email;
    private String phone;
    private String password;


}

package com.example.employeerecord.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dept_id")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long dept_id;
    private String dept_name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference(value = "dept-emp")
    private List<Employees> employees = new ArrayList<>();
}

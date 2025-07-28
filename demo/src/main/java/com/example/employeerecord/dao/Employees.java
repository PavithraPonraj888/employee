package com.example.employeerecord.dao;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "empId")
public class Employees {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long empId;
    private String name;
    private String email;
    private String phone;
    private String password;
    @ManyToOne
    @JoinColumn(name = "dept_id")
   // @JsonBackReference(value = "dept-emp")
    private Department department;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonManagedReference(value = "emp-profile")
    private UserProfile userProfile;
    @ManyToMany
    @JoinTable(name = "Employee-Project", joinColumns = @JoinColumn(name = "emp_id"),inverseJoinColumns = @JoinColumn(name = "projId"))
    //@JsonIgnore
    private List<Project> projects;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private SalaryInfo salaryInfo;
}


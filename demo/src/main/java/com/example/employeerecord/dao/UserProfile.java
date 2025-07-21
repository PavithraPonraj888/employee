package com.example.employeerecord.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class UserProfile {

    @Id
    private Long profileId;

    private String gender;
    private int age;
    private LocalDate joinDate;
    private String location;

    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id")
    //@JsonBackReference(value = "emp-profile")
    @JsonIgnoreProperties({"userProfile", "department"})
    private Employees employee;
}



package com.example.employeerecord.dao;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "profileId")
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
   // @JsonIgnoreProperties({"userProfile", "department"})
    private Employees employee;
}



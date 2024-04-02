package com.cognizant.employeeselfservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_AGE")
    private String employeeAge;

    @ElementCollection
    @Column(name = "EMPLOYEE_SKILLSET")
    private List<String> employeeSkillSet;

    @Column(name = "EMPLOYEE_PHONE_NUMBER")
    private String employeePhoneNumber;

    @Column(name = "EMPLOYEE_ADDRESS")
    private String employeeAddress;
}

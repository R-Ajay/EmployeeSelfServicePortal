package com.cognizant.employeeselfservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeVerificationRequest {

    private String employeeName;

    private String employeeAge;

    private List<String> employeeSkillSet;

    private String employeePhoneNumber;

    private String employeeAddress;
}

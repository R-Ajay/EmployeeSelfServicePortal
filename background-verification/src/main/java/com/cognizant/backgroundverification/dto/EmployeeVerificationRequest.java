package com.cognizant.backgroundverification.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVerificationRequest {

    private String employeeName;

    private String employeeAge;

    private List<String> employeeSkillSet;

    private String employeePhoneNumber;

    private String employeeAddress;
}

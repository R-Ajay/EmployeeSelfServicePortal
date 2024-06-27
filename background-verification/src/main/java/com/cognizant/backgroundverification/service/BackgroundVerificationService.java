package com.cognizant.backgroundverification.service;


import com.cognizant.backgroundverification.dto.EmployeeVerificationRequest;

public interface BackgroundVerificationService {

    public String verifyEmployeeBackground(EmployeeVerificationRequest employeeVerificationRequest);
}

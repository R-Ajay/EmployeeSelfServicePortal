package com.cognizant.backgroundverification.service;


import com.cognizant.backgroundverification.dto.EmployeeVerificationRequest;
import org.springframework.stereotype.Service;


public interface BackgroundVerificationService {
    public String verifyEmployeeBackground(EmployeeVerificationRequest employeeVerificationRequest);
}

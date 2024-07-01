package com.cognizant.backgroundverification.service;

import com.cognizant.backgroundverification.dto.EmployeeVerificationRequest;
import org.springframework.stereotype.Service;

import java.util.Random;


public class BackgroundVerificationServiceImpl implements BackgroundVerificationService{
    
    @Override
    public String verifyEmployeeBackground(EmployeeVerificationRequest employeeVerificationRequest) {

        Random random = new Random();
        return  random.nextInt(2) == 0 ?  "Good" : "Bad";
    }
}

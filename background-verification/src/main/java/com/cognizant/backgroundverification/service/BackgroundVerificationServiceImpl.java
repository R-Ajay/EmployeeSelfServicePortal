package com.cognizant.backgroundverification.service;

import com.cognizant.backgroundverification.dto.EmployeeVerificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class BackgroundVerificationServiceImpl implements BackgroundVerificationService{


    @Override
    public String verifyEmployeeBackground(EmployeeVerificationRequest employeeVerificationRequest) {

        Random random = new Random();
        return  random.nextInt(2) == 0 ?  "Good" : "Bad";
    }
}

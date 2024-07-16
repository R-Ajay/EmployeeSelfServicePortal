package com.cognizant.employeeselfservice.feign;

import com.cognizant.employeeselfservice.dto.EmployeeVerificationRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements BackgroundVerification{
    @Override
    public ResponseEntity<String> verifyEmployeeBackground(EmployeeVerificationRequest employeeVerificationRequest) {
        return new ResponseEntity<>
                ("Background Verification is temporarily down. Please try again after sometimes",
                         HttpStatusCode.valueOf(500));
    }
}

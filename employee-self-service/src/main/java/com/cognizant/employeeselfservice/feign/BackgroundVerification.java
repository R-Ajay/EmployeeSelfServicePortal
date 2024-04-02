package com.cognizant.employeeselfservice.feign;

import com.cognizant.employeeselfservice.dto.EmployeeVerificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "BACKGROUND-VERIFICATION")
public interface BackgroundVerification {

    @PostMapping(name = "/verify")
    public ResponseEntity<String> verifyEmployeeBackground(@RequestBody EmployeeVerificationRequest
                                                                   employeeVerificationRequest);
}

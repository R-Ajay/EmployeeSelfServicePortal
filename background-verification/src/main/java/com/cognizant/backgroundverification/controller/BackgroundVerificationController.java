package com.cognizant.backgroundverification.controller;

import com.cognizant.backgroundverification.dto.EmployeeVerificationRequest;
import com.cognizant.backgroundverification.service.BackgroundVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BackgroundVerificationController {

    @Autowired
    private BackgroundVerificationService backgroundVerificationService;

    @PostMapping(name = "/verify")
    public ResponseEntity<String> verifyEmployeeBackground(@RequestBody EmployeeVerificationRequest
                                                                           employeeVerificationRequest){
        log.info("VerifyEmployeeBackground api is called");
        return new ResponseEntity<>(backgroundVerificationService
                             .verifyEmployeeBackground(employeeVerificationRequest), HttpStatus.OK);
    }
}

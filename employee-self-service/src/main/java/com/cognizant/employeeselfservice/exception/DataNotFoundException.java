package com.cognizant.employeeselfservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String errorMessage;
}

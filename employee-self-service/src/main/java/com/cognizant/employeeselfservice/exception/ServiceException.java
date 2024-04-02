package com.cognizant.employeeselfservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException {

    private Date timeStamp;
    private String message;
    private String description;
}

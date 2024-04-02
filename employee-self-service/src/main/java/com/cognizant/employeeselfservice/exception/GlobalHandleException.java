package com.cognizant.employeeselfservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ServiceException> handleDataNotFoundException(DataNotFoundException exception,
                                                                        WebRequest webRequest) {

        ServiceException serviceException =
                new ServiceException(new Date(),
                        exception.getErrorMessage(),
                        webRequest.getDescription(false));

       return new ResponseEntity<>(serviceException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionClass(Exception e, WebRequest webRequest) {
        ServiceException serviceException = new ServiceException(new Date(), e.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(serviceException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

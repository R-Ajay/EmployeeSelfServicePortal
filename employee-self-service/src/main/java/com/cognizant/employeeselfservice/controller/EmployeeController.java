package com.cognizant.employeeselfservice.controller;

import com.cognizant.employeeselfservice.model.Employee;
import com.cognizant.employeeselfservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employees", produces = "application/json")
    @Operation(summary = "Get all the employees detail")
    public ResponseEntity<List<Employee>> getAllEmployee(){

        log.info("GetAllEmployee api is called");
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{employeeId}", produces = "application/json")
    @Operation(summary = "Get employee by id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId){

        log.info("GetEmployeeById api is called");
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping(path = "/employee", produces = "application/json")
    @Operation(summary = "Get employee by name")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("employeeName") String employeeName){

        log.info("GetEmployeeByName api is called");
        return new ResponseEntity<>(employeeService.getEmployeeByName(employeeName), HttpStatus.OK);
    }

    @PostMapping(path = "/employee", consumes = "application/json")
    @Operation(summary = "Add employee details")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){

        log.info("AddEmployee api is called");
        String result = employeeService.addEmployee(employee);
        if("Employee verification failed".equalsIgnoreCase(result))
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result ,HttpStatus.CREATED);
    }



    @PutMapping(path = "/employee/{employeeId}")
    @Operation(summary = "Update employee by id")
    public ResponseEntity<String> updateEmployee(@PathVariable("employeeId") String employeeId,
                                                       @RequestBody Employee employee){

        log.info("UpdateEmployee api is called");
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId ,employee) ,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/employee/{employeeId}")
    @Operation(summary = "Delete employee by id")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") String employeeId){

        log.info("DeleteEmployee api is called");
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId) ,HttpStatus.ACCEPTED);
    }

}

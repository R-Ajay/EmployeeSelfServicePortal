package com.cognizant.employeeselfservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cognizant.employeeselfservice.exception.DataNotFoundException;
import com.cognizant.employeeselfservice.feign.BackgroundVerification;
import com.cognizant.employeeselfservice.model.Employee;
import com.cognizant.employeeselfservice.repository.EmployeeRepo;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private BackgroundVerification backgroundVerification;


    @Test
    @DisplayName("GetEmployeeById_SuccessScenario")
    void getEmployeeByIdSuccess() {

        when(employeeRepo.findByEmployeeId(anyString())).thenReturn(getEmployee());

        Employee employee = employeeServiceImpl.getEmployeeById("18789");

         assertEquals(employee.getEmployeeId(),"18789");
         assertEquals(employee.getEmployeeName(),"Ajay R");
         assertEquals(employee.getEmployeeAge(),"23");
         assertEquals(employee, getEmployee());
    }

    @Test
    @DisplayName("GetEmployeeByName_SuccessScenario")
    void getEmployeeByNameSuccess() {

       when(employeeRepo.findByEmployeeName(anyString())).thenReturn(getEmployeeLists());

        List<Employee> employees = employeeServiceImpl.getEmployeeByName("Ajay R");

        assertEquals(employees.size(), 2);
    }

    @Test
    @DisplayName("GetEmployeeById_FailScenario")
    void getEmployeeByIdFail() {
        when(employeeRepo.findByEmployeeId(anyString())).thenReturn(null);

       assertThrows(DataNotFoundException.class, () ->
                 employeeServiceImpl.getEmployeeById("18123") );
    }

    @Test
    @DisplayName("GetEmployeeByName_FailScenario")
    void getEmployeeByNameFail() {

       when(employeeRepo.findByEmployeeName(anyString())).thenReturn(null);

        assertThrows(DataNotFoundException.class, () ->
                      employeeServiceImpl.getEmployeeByName("Ajay R"));
    }

    //create a test case for getAllEmployeeSuccess
    @Test
    @DisplayName("GetAllEmployee_SuccessScenario")
    void getAllEmployeeSuccess() {
        when(employeeRepo.findAll()).thenReturn(getEmployeeLists());

        List<Employee> employees = employeeServiceImpl.getAllEmployee();

        assertEquals(employees.size(), 2);
    }

    //create a test case for getAllEmployeeFail
    @Test
    @DisplayName("GetAllEmployee_FailScenario")
    void getAllEmployeeFail() {
        when(employeeRepo.findAll()).thenReturn(null);

        assertThrows(DataNotFoundException.class, () ->
                employeeServiceImpl.getAllEmployee());
    }

    //create a test case for addEmployeeSuccess
    @Test
    @DisplayName("AddEmployee_SuccessScenario")
    void addEmployeeSuccess() {
        Employee employee = getEmployee();
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
        when(backgroundVerification.verifyEmployeeBackground(any())).thenReturn(new ResponseEntity<>("Good", HttpStatus.OK));
        String result = employeeServiceImpl.addEmployee(employee);

         assertTrue(result.contains("successfully added to db"));
    }

    //create a test case for addEmployeeFail
    @Test
    @DisplayName("AddEmployee_FailScenario")
    void addEmployeeFail() {
        Employee employee = getEmployee();
        when(backgroundVerification.verifyEmployeeBackground(any())).thenReturn(new ResponseEntity<>("Bad", HttpStatus.BAD_REQUEST));
        String result = employeeServiceImpl.addEmployee(employee);
        assertEquals(result, "Employee verification failed");
    }

    //Create a test case for deleteEmployeeSuccess
    @Test
    @DisplayName("DeleteEmployee_SuccessScenario")
    void deleteEmployeeSuccess() {
        when(employeeRepo.findByEmployeeId(anyString())).thenReturn(getEmployee());
        String result = employeeServiceImpl.deleteEmployee("18789");
        assertEquals(result, "Given employee : 18789 is successfully removed from db");
    }

    //create a test case for deleteEmployeeFail
    @Test
    @DisplayName("DeleteEmployee_FailScenario")
    void deleteEmployeeFail() {
        when(employeeRepo.findByEmployeeId(anyString())).thenReturn(null);
        assertThrows(DataNotFoundException.class, () ->
                employeeServiceImpl.deleteEmployee("18123"));
    }


    private Employee getEmployee(){
        Employee employee = new Employee();
        List<String> skillSets = Arrays.asList("Java", "Spring Boot");
        employee.setEmployeeId("18789");
        employee.setEmployeeName("Ajay R");
        employee.setEmployeeAge("23");
        employee.setEmployeePhoneNumber("+91 789065432");
        employee.setEmployeeAddress("S.S Colony 2nd street,Madurai-16");
        employee.setEmployeeSkillSet(skillSets);
        return employee;
    }

    private List<Employee> getEmployeeLists(){
        List<Employee> employees = new ArrayList<>();

        Employee employee = new Employee();
        List<String> skillSets = Arrays.asList("Java", "Spring Boot");
        employee.setEmployeeId("18789");
        employee.setEmployeeName("Ajay R");
        employee.setEmployeeAge("23");
        employee.setEmployeePhoneNumber("+91 789065432");
        employee.setEmployeeAddress("S.S Colony 2nd street,Madurai-16");
        employee.setEmployeeSkillSet(skillSets);


        Employee employee1 = new Employee();
        employee1.setEmployeeId("72813");
        employee.setEmployeeName("Ajay R");
        employee1.setEmployeeAge("23");
        employee1.setEmployeePhoneNumber("+91 789065432");
        employee1.setEmployeeAddress("Arockiyamatha street,Madurai-16");
        employee1.setEmployeeSkillSet(skillSets);

        employees.add(employee);
        employees.add(employee1);

        return employees;
    }
}
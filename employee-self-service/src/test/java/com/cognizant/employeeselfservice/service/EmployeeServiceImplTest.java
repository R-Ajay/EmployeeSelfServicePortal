package com.cognizant.employeeselfservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cognizant.employeeselfservice.exception.DataNotFoundException;
import com.cognizant.employeeselfservice.model.Employee;
import com.cognizant.employeeselfservice.repository.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;


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
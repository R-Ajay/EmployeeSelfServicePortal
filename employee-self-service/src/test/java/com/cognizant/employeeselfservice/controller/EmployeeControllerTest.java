package com.cognizant.employeeselfservice.controller;

import com.cognizant.employeeselfservice.model.Employee;
import com.cognizant.employeeselfservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GetEmployeeById_SuccessScenario")
    void getEmployeeByIdSuccess() throws Exception {


        String employeeId="18789";
        Employee expectedEmployee = getEmployee();

        when(employeeService.getEmployeeById(anyString())).thenReturn(expectedEmployee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/{employeeId}",employeeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(expectedEmployee.getEmployeeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(expectedEmployee.getEmployeeName()))
                .andReturn();

    }

    @Test
    @DisplayName("GetEmployeeByName_SuccessScenario")
    void getEmployeeByNameSuccess() throws Exception {

        String employeeName = "Ajay R";
        List<Employee> expectedEmployees = getEmployeeLists();
        when(employeeService.getEmployeeByName(anyString())).thenReturn(getEmployeeLists());

        mockMvc.perform(MockMvcRequestBuilders.get("/employee?employeeName={employeeName}", employeeName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeId").value(expectedEmployees.get(0).getEmployeeId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].employeeId").value(expectedEmployees.get(1).getEmployeeId()))
                .andReturn();
    }

    @Test
    @DisplayName("UpdateEmployee_SuccessScenario")
    void updateEmployeeSuccess() throws Exception {
        String employeeId = "18789";
        Employee employeeToUpdate = getEmployee();
        String expectedResponse = "Employee updated successfully";

        when(employeeService.updateEmployee(anyString(), any(Employee.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/employee/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    @Test
    @DisplayName("UpdateEmployee_FailureScenario")
    void updateEmployeeFailure() throws Exception {
        String employeeId = "18789";
        Employee employeeToUpdate = getEmployee();
        String expectedResponse = "Employee not found";

        when(employeeService.updateEmployee(anyString(), any(Employee.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/employee/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeToUpdate)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }
    //Create a test case for addEmployeeSuccess
    @Test
    @DisplayName("AddEmployee_SuccessScenario")
    void addEmployeeSuccess() throws Exception {
        Employee employee = getEmployee();
        String expectedResponse = "Employee added successfully";

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    //Create a test case for addEmployeeFailure
    @Test
    @DisplayName("AddEmployee_FailureScenario")
    void addEmployeeFailure() throws Exception {
        Employee employee = getEmployee();
        String expectedResponse = "Employee verification failed";

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    //Create a test case for deleteEmployeeSuccess
    @Test
    @DisplayName("DeleteEmployee_SuccessScenario")
    void deleteEmployeeSuccess() throws Exception {
        String employeeId = "18789";
        String expectedResponse = "Employee deleted successfully";

        when(employeeService.deleteEmployee(anyString())).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{employeeId}", employeeId))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
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
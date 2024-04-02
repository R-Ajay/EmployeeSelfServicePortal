package com.cognizant.employeeselfservice.service;

import com.cognizant.employeeselfservice.model.Employee;

import java.util.List;

/**
 * This is employee self service class
 * @author Ajay
 */
public interface EmployeeService {
   public List<Employee> getAllEmployee();

   public Employee getEmployeeById(String employeeId);

   public List<Employee> getEmployeeByName(String employeeName);

   public String addEmployee(Employee employee);

   public String updateEmployee(String employeeId, Employee employee);

   public String deleteEmployee(String employeeId);


}

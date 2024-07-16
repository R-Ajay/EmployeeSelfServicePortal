package com.cognizant.employeeselfservice.service;

import com.cognizant.employeeselfservice.dto.EmployeeVerificationRequest;
import com.cognizant.employeeselfservice.exception.DataNotFoundException;
import com.cognizant.employeeselfservice.feign.BackgroundVerification;
import com.cognizant.employeeselfservice.model.Employee;
import com.cognizant.employeeselfservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BackgroundVerification backgroundVerification;

    private static final String URL = "http://BACKGROUND-VERIFICATION/verify";

    /*
    * This method get all the employees from the db
    */
    @Override
    public List<Employee> getAllEmployee() {

      List<Employee> employees = employeeRepo.findAll();
        if(employees == null || employees.isEmpty())
            throw new DataNotFoundException(404, "Oops! No Data is available in db");

        return employees;
    }

    /*
     * This method get the employee based on employee id
     * @Param Employee Id
     */
    @Override
    public Employee getEmployeeById(String employeeId) {
        Employee employee = employeeRepo.findByEmployeeId(employeeId);

        if (employee == null)
            throw new DataNotFoundException(404, "Oops! Employee data is not available in db");

        return employee;
    }

    /*
     * This method get the employee based on employee name
     * @Param Employee Name
     */
    @Override
    public List<Employee> getEmployeeByName(String employeeName) {
        List<Employee> employees = employeeRepo.findByEmployeeName(employeeName);

        if (employees == null || employees.isEmpty())
            throw new DataNotFoundException(404, "Oops! Employee data is not available in db");

        return employees;
    }

    /*
     * This method add the employee to the db
     * @Param Employee Details
     */
    @Override
    public String addEmployee(Employee employee) {

        EmployeeVerificationRequest employeeVerificationRequest =
                EmployeeVerificationRequest
                        .builder()
                        .employeeName(employee.getEmployeeName())
                        .employeeAge(employee.getEmployeeAge())
                        .employeeSkillSet(employee.getEmployeeSkillSet())
                        .employeePhoneNumber(employee.getEmployeePhoneNumber())
                        .employeeAddress(employee.getEmployeeAddress())
                        .build();

        ResponseEntity<String> responseEntity = backgroundVerification
                                    .verifyEmployeeBackground(employeeVerificationRequest);

        if ("Bad".equalsIgnoreCase(responseEntity.getBody())){
           return "Employee verification failed";
       } else if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(500)) {
            return responseEntity.getBody();
        }


        String employeeId = generateEmployeeId();
        employee.setEmployeeId(employeeId);
        employeeRepo.save(employee);


        return "Given employee : " + employeeId + " is successfully added to db";
    }

    /*
     * This method update the employee in the db
     * @Param Employee Id
     * @Param Employee Details
     */
    @Override
    public String updateEmployee(String employeeId, Employee employee) {

        Employee existingEmployee = employeeRepo.findByEmployeeId(employeeId);

        if(existingEmployee == null)
            throw new DataNotFoundException(404, "Oops! Employee data is not available in db");

        employee.setEmployeeId(existingEmployee.getEmployeeId());
        employee.setId(existingEmployee.getId());
        employeeRepo.save(employee);

        return "Given employee : " + existingEmployee.getEmployeeId() + " is successfully updated in db";
    }


    /*
     * This method delete the employee in the db
     * @Param Employee Id
     */

    @Override
    public String deleteEmployee(String employeeId) {
        Employee existingEmployee = employeeRepo.findByEmployeeId(employeeId);

        if(existingEmployee == null)
            throw new DataNotFoundException(404, "Oops! Employee data is not available in db");

        employeeRepo.delete(existingEmployee);

        return "Given employee : " + existingEmployee.getEmployeeId() + " is successfully removed from db";
    }


    /*
     * This method generate the unique employee id
     */
    private String generateEmployeeId(){

        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000;
        return String.valueOf(randomNumber);

    }
}

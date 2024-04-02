package com.cognizant.employeeselfservice.repository;

import com.cognizant.employeeselfservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query(name="SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = :employeeId ",nativeQuery = true)
    public Employee findByEmployeeId(@Param("employeeId") String employeeId);

    @Query(name="SELECT * FROM EMPLOYEE WHERE EMPLOYEE_NAME = :employeeName ",nativeQuery = true)
    public List<Employee> findByEmployeeName(@Param("employeeName") String employeeName);


}

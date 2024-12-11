package com.example.employeespringboot.service;

import com.example.employeespringboot.model.Employee;
import com.example.employeespringboot.model.EmployeeHistory;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id );

    List<EmployeeHistory> getEmployeesHistory();

    void saveUpdateHistory(Employee employee);

    void restoreEmployeebyId(long id);

    EmployeeHistory getEmployeeHistoryById(long id);
}

package com.example.employeespringboot.service;
import com.example.employeespringboot.model.Employee;
import com.example.employeespringboot.model.EmployeeHistory;
import com.example.employeespringboot.repository.EmployeeHistoryRepository;
import com.example.employeespringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeHistoryRepository employeeHistoryRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public EmployeeHistory getEmployeeHistoryById(long id) {
        Optional<EmployeeHistory> optional = employeeHistoryRepository.findById(id);
        EmployeeHistory employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = getEmployeeById(id);
        EmployeeHistory  employeeHistory = new EmployeeHistory();
        employeeHistory.setId(employee.getId());
        employeeHistory.setFirstName(employee.getFirstName());
        employeeHistory.setLastName(employee.getLastName());
        employeeHistory.setEmail(employee.getEmail());

        employeeHistoryRepository.save(employeeHistory);

        this.employeeRepository.deleteById(id);

    }

    @Override
    public List<EmployeeHistory> getEmployeesHistory() {
        return employeeHistoryRepository.findAll();
    }

    @Override
    public void saveUpdateHistory(Employee employee) {
        EmployeeHistory  employeeHistory = new EmployeeHistory();
        employeeHistory.setId(employee.getId());
        employeeHistory.setFirstName(employee.getFirstName());
        employeeHistory.setLastName(employee.getLastName());
        employeeHistory.setEmail(employee.getEmail());

        employeeHistoryRepository.save(employeeHistory);
    }

    @Override
    public void restoreEmployeebyId(long id) {
        EmployeeHistory employeeHistory  = getEmployeeHistoryById(id);
        Employee employee = new Employee();
        employee.setId(employeeHistory.getId());
        employee.setFirstName(employeeHistory.getFirstName());
        employee.setLastName(employeeHistory.getLastName());
        employee.setEmail(employeeHistory.getEmail());

        employeeRepository.save(employee);
        employeeHistoryRepository.deleteById(id);

    }
}



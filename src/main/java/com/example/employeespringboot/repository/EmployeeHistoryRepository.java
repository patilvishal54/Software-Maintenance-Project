package com.example.employeespringboot.repository;

import com.example.employeespringboot.model.Employee;
import com.example.employeespringboot.model.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory,Long> {
}

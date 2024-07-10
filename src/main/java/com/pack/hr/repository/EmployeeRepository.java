package com.pack.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.hr.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

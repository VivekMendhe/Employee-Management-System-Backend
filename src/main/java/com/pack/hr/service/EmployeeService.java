package com.pack.hr.service;

import java.util.List;

import com.pack.hr.dto.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

	void deleteEmployee(Long id);

	EmployeeDTO getEmployeeById(Long id);

	List<EmployeeDTO> getAllEmployees();
}

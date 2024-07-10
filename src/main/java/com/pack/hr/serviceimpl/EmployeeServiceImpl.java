package com.pack.hr.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.hr.dto.AddressDTO;
import com.pack.hr.dto.EmployeeDTO;
import com.pack.hr.entities.Address;
import com.pack.hr.entities.Employee;
import com.pack.hr.exception.ResourceNotFoundExcetion;
import com.pack.hr.repository.EmployeeRepository;
import com.pack.hr.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmployeeRepository employeeRepository;

	/*
	 * @Override public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
	 * Employee employee = dtoToEntity(employeeDTO); Employee savedEmployee =
	 * employeeRepository.save(employee); return entityToDTO(savedEmployee); }
	 */

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = dtoToEntity(employeeDTO);

		// Map and set new addresses
		List<Address> addresses = mapAddressDTOsToEntities(employeeDTO.getAddresses(), employee);
		employee.setAddresses(addresses);

		Employee savedEmployee = employeeRepository.save(employee);
		return entityToDTO(savedEmployee);
	}

	// Helper method to map AddressDTOs to Address entities and set the employee
	private List<Address> mapAddressDTOsToEntities(List<AddressDTO> addressDTOs, Employee employee) {
		return addressDTOs.stream().map(addressDTO -> {
			Address address = new Address(); // creating object of address
			address.setCity(addressDTO.getCity());
			address.setEffectiveDate(LocalDateTime.now()); // Set EffectiveDate to CurrentDateTime
			address.setEndDate(null); // Set EndDate to NULL
			address.setEmployee(employee);
			return address;
		}).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		// Update basic employee details
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setContact(employeeDTO.getContact());

		// Update addresses
		updateAddresses(employee, employeeDTO.getAddresses());

		// Save updated employee
		Employee updatedEmployee = employeeRepository.save(employee);

		return entityToDTO(updatedEmployee);
	}

	// Helper method to update addresses
	private void updateAddresses(Employee employee, List<AddressDTO> list) {
		// Clear existing addresses
		employee.getAddresses().clear();

		// Map and set new addresses
		if (list != null) {
			List<Address> addresses = list.stream().map(addressDTO -> {
				Address address = modelMapper.map(addressDTO, Address.class);
				address.setEmployee(employee);
				return address;
			}).collect(Collectors.toList());
			employee.getAddresses().addAll(addresses);
		}
	}

	@Override
	public void deleteEmployee(Long id) {
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundExcetion("Id is not matched with employee id: " + id);
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		return entityToDTO(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	// convert dto to entity
	public Employee dtoToEntity(EmployeeDTO employeeDTO) {
		return modelMapper.map(employeeDTO, Employee.class);
	}

	// convert entity to dto
	public EmployeeDTO entityToDTO(Employee employee) {
		return modelMapper.map(employee, EmployeeDTO.class);
	}
}

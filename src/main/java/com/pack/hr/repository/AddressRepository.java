package com.pack.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.hr.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	List<Address> findByEmployeeId(Long employeeId);
	List<Address> findByEmployeeIdAndEndDateIsNull(Long employeeId);
}

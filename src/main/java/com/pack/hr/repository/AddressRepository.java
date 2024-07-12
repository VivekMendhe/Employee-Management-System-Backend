package com.pack.hr.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.hr.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByEmployeeId(Long employeeId);

	List<Address> findByEmployeeIdAndEndDateIsNull(Long employeeId);

	@Query("SELECT a FROM Address a WHERE a.employee.name = :employeeName "
			+ "AND a.effectiveDate <= :toDate AND (a.endDate IS NULL OR a.endDate >= :fromDate)")
	List<Address> findByEmployeeNameAndDateRange(@Param("employeeName") String employeeName,
			@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}

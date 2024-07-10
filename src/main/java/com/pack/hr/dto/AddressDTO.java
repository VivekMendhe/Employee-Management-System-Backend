package com.pack.hr.dto;

import java.time.LocalDateTime;

public class AddressDTO {

	private Long id;
	private String city;
	private LocalDateTime effectiveDate;
	private LocalDateTime endDate;
	private Long employeeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public LocalDateTime getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	
	
}

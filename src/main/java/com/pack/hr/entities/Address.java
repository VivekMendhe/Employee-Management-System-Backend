package com.pack.hr.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String city;
	private LocalDateTime effectiveDate;
	private LocalDateTime endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Long id, String city, LocalDateTime effectiveDate, LocalDateTime endDate, Employee employee) {
		super();
		this.id = id;
		this.city = city;
		this.effectiveDate = effectiveDate;
		this.endDate = endDate;
		this.employee = employee;
	}

	public Address(String city, LocalDateTime effectiveDate, LocalDateTime endDate, Employee employee) {
		super();
		this.city = city;
		this.effectiveDate = effectiveDate;
		this.endDate = endDate;
		this.employee = employee;
	}

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
}

package com.pack.hr.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String contact;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, String name, String email, String contact, List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.addresses = addresses;
	}

	public Employee(String name, String email, String contact, List<Address> addresses) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}

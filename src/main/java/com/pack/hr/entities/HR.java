package com.pack.hr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HR {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String city;
	private String country;

	public HR() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HR(Long id, String name, String email, String password, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
	}

	public HR(String name, String email, String password, String city, String country) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.city = city;
		this.country = country;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "HR [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", city=" + city
				+ ", country=" + country + "]";
	}

}

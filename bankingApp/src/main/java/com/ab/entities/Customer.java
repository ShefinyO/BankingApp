package com.ab.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	private String name;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "customer")
	private List<BankAccount> allAccounts;
	
	
	public Customer() {
		
	}
	
	public Customer(int customerId) {
		this.customerId = customerId;
	}
	
	public Customer(String name, String email, String password) {
		
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Customer(String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	
	public Customer(int customerId, String name, String email, String password) {
		
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	

	public int getCustomerId() {
		return this.customerId;
	}
	

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}

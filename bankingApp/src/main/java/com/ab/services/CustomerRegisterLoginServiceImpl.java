package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ab.entities.Customer;
import com.ab.repositories.CustomerRepository;

@Service
public class CustomerRegisterLoginServiceImpl implements CustomerRegisterLoginService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer registerCustomer(Customer customer) {
		
		Customer newCustomer = customerRepository.save(customer);
		
		return newCustomer;
		
	}
	
	
	@Override
	public Customer loginCustomer(Customer customer) {
		
		Customer loggedInCustomer = customerRepository.checkLoginCredentials(customer.getEmail(), customer.getPassword());
		
		return loggedInCustomer;
		
	}
	
	
	

}

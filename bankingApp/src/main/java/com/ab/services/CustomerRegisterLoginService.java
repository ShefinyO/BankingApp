package com.ab.services;

import com.ab.entities.Customer;


public interface CustomerRegisterLoginService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer loginCustomer(Customer customer);

}

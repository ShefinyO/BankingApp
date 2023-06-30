package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entities.Customer;
import com.ab.services.CustomerRegisterLoginService;

@RestController
public class CustomerController {

	
	@Autowired
	private CustomerRegisterLoginService customerRegisterLoginService;
	
	
	@PostMapping("/register")
	private ResponseEntity<Customer> registerCustomer(@RequestBody Customer c) {
		
		Customer newCustomer = customerRegisterLoginService.registerCustomer(c);
		
		if(newCustomer != null) return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
		
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/login")
	private ResponseEntity<Customer> loginCustomer(@RequestBody Customer c) {
		
		Customer newCustomer = customerRegisterLoginService.loginCustomer(c);
		
		if(newCustomer != null) return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);
		
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		
	}
	
	
}

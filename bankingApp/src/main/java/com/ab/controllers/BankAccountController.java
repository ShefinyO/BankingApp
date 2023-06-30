package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.Customer;
import com.ab.entities.SavingsAccount;
import com.ab.services.BankAccountService;

@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	
	@PostMapping("/current")
	public ResponseEntity<BankAccount> createCurrentAccount(@RequestBody Customer c){
		
		//CurrentAccount currentAccount = new CurrentAccount(c);
		
		BankAccount newCurrentAccount = bankAccountService.createCurrentAccount(c);
		
		if(newCurrentAccount != null)return new ResponseEntity<BankAccount>(newCurrentAccount, HttpStatus.CREATED);
		
		return new ResponseEntity<BankAccount>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/savings")
	public ResponseEntity<BankAccount> createSavingsAccount(@RequestBody Customer c){
		
		//SavingsAccount savingsAccount = new SavingsAccount(c);
		
		BankAccount newCurrentAccount = bankAccountService.createSavingsAccount(c);
		
		if(newCurrentAccount != null)return new ResponseEntity<BankAccount>(newCurrentAccount, HttpStatus.CREATED);
		
		return new ResponseEntity<BankAccount>(HttpStatus.NO_CONTENT);
		
	}

}

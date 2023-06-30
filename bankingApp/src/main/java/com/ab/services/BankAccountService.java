package com.ab.services;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.Customer;
import com.ab.entities.SavingsAccount;

public interface BankAccountService {
	
	
	public BankAccount createCurrentAccount(Customer c);
	
	public BankAccount createSavingsAccount(Customer c);
	
	

}

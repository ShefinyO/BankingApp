package com.ab.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends BankAccount{
	
	private double interestRate;

	public SavingsAccount(String accountNumber, Set<Transaction> allTransactions,
			double balance, LocalDateTime dateOpened, Customer customer, double interestRate) {
		
		super(accountNumber, allTransactions, balance, dateOpened, customer);
		
		this.interestRate = interestRate;
		
	
		
	}
	
	public SavingsAccount() {
		
		super();
	}
	
	public SavingsAccount(Customer c) {
		
		super(c);
		
	}
	
	
	
	public double getInterestRate() {
		return interestRate;
	}
	
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	
	@Override
	public String toString() {
		return super.toString() + "Interest Rate : " + interestRate;
	}
	
}

package com.ab.entities;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.Entity;


@Entity
public class CurrentAccount extends BankAccount{
	
	private double overDraftLimit;
	
	private double overDraftBalance;
	
	public CurrentAccount(String accountNumber, Set<Transaction> allTransactions,
			double balance, LocalDateTime dateOpened, double overdraftLimit, double overDraftBalance, Customer customer) {
		
		super(accountNumber, allTransactions, balance, dateOpened, customer);
		
		this.overDraftLimit = overdraftLimit;
		
		this.overDraftBalance = overDraftBalance;
		
	}
	
	public CurrentAccount(String accountNumber,
			double balance, LocalDateTime dateOpened, double overdraftLimit, double overDraftBalance) {
		
		super(accountNumber, balance, dateOpened);
		
		this.overDraftLimit = overdraftLimit;
		
		this.overDraftBalance = overDraftBalance;
		
	}
	
	public CurrentAccount(String accountNumber, Set<Transaction> allTransactions,
			double balance, LocalDateTime dateOpened, double overdraftLimit, double overDraftBalance) {
		
		super(accountNumber, allTransactions, balance, dateOpened);
		
		this.overDraftLimit = overdraftLimit;
		
		this.overDraftBalance = overDraftBalance;
		
	}
	
	
	public CurrentAccount(double overDraftLimit) {
		
		this.overDraftLimit = overDraftLimit;
		
	}
	
	public CurrentAccount(Customer customer) {
		
		super(customer);
		
		
	}
	
	public CurrentAccount() {
		
		super();
		
		
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public double getOverDraftBalance() {
		return overDraftBalance;
	}
	
	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
	
	public void setOverDraftBalance(Double overDraftBalance) {
		this.overDraftBalance = overDraftBalance;
	}

	@Override
	public String toString() {
		return super.toString() + "OverDraftLimit : " + this.overDraftLimit;
	}

	
}

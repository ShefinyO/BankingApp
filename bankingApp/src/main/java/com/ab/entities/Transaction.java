package com.ab.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@ManyToOne
	@JoinColumn(name="account_number")
	private BankAccount bankAccount;
	
	private String transactionType;
	private double amount;
	private double balance;
	private LocalDateTime dateOfTransaction;
	private double overDraftBalance;
	
	public Transaction() {
		
	}
	
	public Transaction(int transactionId, BankAccount bankAccount, String transactionType, double amount,
			double balance, LocalDateTime dateOfTransaction, double overDraftBalance) {
		super();
		this.transactionId = transactionId;
		this.bankAccount = bankAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
		this.dateOfTransaction = dateOfTransaction;
		this.overDraftBalance = overDraftBalance;
	}
	
	public Transaction(BankAccount bankAccount, String transactionType, double amount,
			double balance, LocalDateTime dateOfTransaction, double overDraftBalance) {
		super();
		this.bankAccount = bankAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
		this.dateOfTransaction = dateOfTransaction;
		this.overDraftBalance = overDraftBalance;
	}
	

	public Transaction(double amount) {
		this.amount = amount;
	}
	
	public Transaction(BankAccount account) {
		this.bankAccount = account;
	}

	public int getTransactionId() {
		return transactionId;
	}



	public String getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}
	
	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public double getOverDraftBalance() {
		return overDraftBalance;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	
	public void setOverDraftBalance(double overDraftBalance) {
		this.overDraftBalance = overDraftBalance;
	}

	

	
		
	
}

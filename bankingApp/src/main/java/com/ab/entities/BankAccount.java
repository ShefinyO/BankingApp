package com.ab.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BankAccount{
	
	@Id
	private String accountNumber;
	
	@OneToMany(mappedBy = "bankAccount")
	private Set<Transaction> allTransactions;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	private double balance;
	private LocalDateTime dateOpened;
	
	@ManyToMany
    @JoinTable(
        name = "account_prize",
        joinColumns = @JoinColumn(name = "account_number"),
        inverseJoinColumns = @JoinColumn(name = "prize_id")
    )
	private Set<Prize> allPrizes;
	
	public BankAccount() {
		
		
	}
	
	public BankAccount(String accountNumber, Set<Transaction> allTransactions, double balance, LocalDateTime dateOpened, Customer customer) {

		this.accountNumber = accountNumber;
		this.allTransactions = allTransactions;
		this.balance = balance;
		this.dateOpened = dateOpened;
		this.customer = customer;
	}
	
	public BankAccount(Customer customer) {
		this.customer = customer;
	}
	
	public BankAccount(String accountNumber, double balance, LocalDateTime dateOpened) {

		this.accountNumber = accountNumber;
		this.balance = balance;
		this.dateOpened = dateOpened;
	}
	
	public BankAccount(String accountNumber, Set<Transaction> allTransactions,double balance, LocalDateTime dateOpened) {

		this.accountNumber = accountNumber;
		this.allTransactions = allTransactions;
		this.balance = balance;
		this.dateOpened = dateOpened;
	}
	

	public BankAccount(String accountNumber) {
		
		this.accountNumber = accountNumber;
		
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public Set<Transaction> getAllTransactions() {
		return allTransactions;
	}


	public double getBalance() {
		return balance;
	}

	public LocalDateTime getDateOpened() {
		return dateOpened;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAllPrizes(Set<Prize> allPrizes) {
		this.allPrizes = allPrizes;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAllTransactions(Set<Transaction> allTransactions) {
		this.allTransactions = allTransactions;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setDateOpened(LocalDateTime dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	public Set<Prize> getAllPrizes() {
		// TODO Auto-generated method stub
		return allPrizes;
	}


	
	
	
}
	
	
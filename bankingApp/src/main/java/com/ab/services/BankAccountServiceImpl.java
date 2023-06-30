package com.ab.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.Customer;
import com.ab.entities.Prize;
import com.ab.entities.SavingsAccount;
import com.ab.entities.Transaction;
import com.ab.repositories.BankAccountRepository;
import com.ab.repositories.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public BankAccount createCurrentAccount(Customer c) {
		
		Random r = new Random();
		
		int id = 0;
		
		boolean found = true;
		
		boolean saved = false;
		
		while(!saved) {
			
			id = r.nextInt(1000);
			
			found = bankAccountRepository.existsById("Cur"+id);
			
			if(found == false) saved = true;
	
			
		}
		
		Customer curC = customerRepository.findById(c.getCustomerId()).get();
		
		CurrentAccount bankAccount = new CurrentAccount(curC);
		
		bankAccount.setAccountNumber("Cur" + id);
		bankAccount.setDateOpened(LocalDateTime.now());
		bankAccount.setOverDraftBalance(500.0);
		bankAccount.setAllTransactions(new HashSet<Transaction>());
		
		BankAccount newBankAccount = bankAccountRepository.save(bankAccount);
		
		return newBankAccount;
		
	}

	@Override
	public BankAccount createSavingsAccount(Customer c) {
		
		Random r = new Random();
		
		int id = 0;
		
		boolean found = true;
		
		boolean saved = false;
		
		while(!saved) {
			
			id = r.nextInt(1000);
			
			found = bankAccountRepository.existsById("Sav"+id);
			
			if(found == false) saved = true;
	
			
		}
		
		Customer savC = customerRepository.findById(c.getCustomerId()).get();
		
		SavingsAccount bankAccount = new SavingsAccount(savC);
		
		bankAccount.setAccountNumber("Sav" + id);
		bankAccount.setDateOpened(LocalDateTime.now());
		bankAccount.setAllTransactions(new HashSet<Transaction>());
		bankAccount.setAllPrizes(new HashSet<Prize>());
		
		BankAccount newBankAccount = bankAccountRepository.save(bankAccount);
		
		return newBankAccount;
		
	}
	
	

}

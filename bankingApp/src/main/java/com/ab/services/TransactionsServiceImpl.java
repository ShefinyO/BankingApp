package com.ab.services;

import java.time.LocalDateTime;
import com.ab.entities.*;


import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.repositories.BankAccountRepository;
import com.ab.repositories.CustomerRepository;
import com.ab.repositories.PrizeRepository;
import com.ab.repositories.TransactionsRepository;
import com.ab.utilities.FindNumRange;
import com.ab.utilities.OfferThePrizeException;
import com.ab.utilities.OverDraftLimitExceededException;

import jakarta.transaction.Transactional;

@Service
public class TransactionsServiceImpl implements TransactionsService{
	
	@Autowired
	private TransactionsRepository transactionRepository;
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Autowired
	private PrizeRepository prizeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Transactional
	@Override
	public Transaction deposit(Map<String, Object> body) {
		
		String accountNum = (String)body.get("accountNumber");
		Double balance = (Double)body.get("balance");
		Double amount = (Double)body.get("amount");
		Double odLimit = (Double)body.get("overDraftLimit");
		
		
		BankAccount ba = bankAccountRepository.findById(accountNum).get();
		
		Transaction t = new Transaction(ba);
		
		LocalDateTime da = LocalDateTime.now();
		
		LocalDateTime date = da.withSecond(0).withNano(0);
		
		double newBalance = balance + amount;
		
		Double odBal = 0.0;
		
		if(odLimit != null) {
			if(newBalance < 0){
			
				odBal =  odLimit + newBalance;
				
			}else {
				odBal = odLimit;
			}
		
		}
		
		t.setTransactionType("deposit");
		t.setDateOfTransaction(date);
		t.setBalance(newBalance);	
		t.setOverDraftBalance(odBal);
		t.setAmount(amount);
		
		
		Transaction result = transactionRepository.save(t);		
		
		ba.getAllTransactions().add(result);
		
		BankAccount y = bankAccountRepository.save(ba);
		
		
		int result3 = bankAccountRepository.updateBalance(newBalance, accountNum);
		
		
		
		int result4 = bankAccountRepository.updateOdBalance(odBal, accountNum);
		
		
		
		return transactionRepository.findById(result.getTransactionId()).get();
		
	}
	
	@Transactional
	@Override
	public Transaction withdrawal(Map<String, Object> body){
		
		String accountNum = (String)body.get("accountNumber");
		Double balance = (Double)body.get("balance");
		Double amount = (Double)body.get("amount");
		Double odLimit = (Double)body.get("overDraftLimit");
		
		BankAccount ba = bankAccountRepository.findById(accountNum).get();
		
		Transaction t = new Transaction(amount);
		
		LocalDateTime date = LocalDateTime.now();
		
		double newBalance = balance - amount;
		
		Double odBal = 0.0;
		
		if(odLimit != null){
			
			if(newBalance < 0){
				odBal = odLimit + newBalance;
				
				try {
					if(odBal < 0){
						
						throw new OverDraftLimitExceededException("OverDraftLimit Exceeded");
	
					}
				}catch(Exception e) {
					Transaction trans = reversal(accountNum, balance, amount, odLimit);
					System.out.println(e.getMessage());
					return trans;
				}
				
				
			
			}else{
				odBal = odLimit;
			}
			
		}
		
		t.setTransactionType("withdrawal");
		t.setDateOfTransaction(date);
		t.setBalance(newBalance);	
		t.setOverDraftBalance(odBal);
		
		Transaction result = transactionRepository.save(t);
		
		int result2 = transactionRepository.updateTransactionAccount(ba, t.getTransactionId());
		
		System.out.println(result2);
	
		
		int result3 = bankAccountRepository.updateBalance(newBalance, accountNum);
		
		System.out.println(result3);
		
		int result4 = bankAccountRepository.updateOdBalance(odBal, accountNum);
		
		System.out.println(result4);
		
		
		return result;
		
		
	}
	
	@Transactional
	@Override
	public Transaction reversal(String accountNum, Double balance, Double amount, Double odLimit) {
		
		Transaction t = new Transaction(amount);
		
		BankAccount ba = new BankAccount(accountNum);
		
		Double odBal = 0.0;
		
		if(balance >= 0) {
			odBal = odLimit;
		}else {
			odBal = odLimit + balance;
		}
		
		t.setBalance(balance);
		t.setDateOfTransaction(LocalDateTime.now());
		t.setOverDraftBalance(odBal);
		t.setTransactionType("reversal");
		
		Transaction result = transactionRepository.save(t);
		
		int result1 = transactionRepository.updateTransactionAccount(ba, t.getTransactionId());
		
		System.out.println(result1);
		
		int result2 = bankAccountRepository.updateBalance(balance, accountNum);
		
		System.out.println(result2);
		
		int result3 = bankAccountRepository.updateOdBalance(odBal, accountNum);
		
		System.out.println(result3);
		
		return result;
	}
	
	
	
	@Transactional
	@Override
	public Prize checkEligibleForPrize(Map<String, Object> body){
		
		String accountNum = (String)body.get("accountNumber");
		Double balance = (Double)body.get("balance");
		Double amount = (Double)body.get("amount");
		
		int id = 0;
		
		FindNumRange r1 = new FindNumRange(balance.intValue());
		
		r1.setRangeStart();
		r1.setRangeEnd();
		
		Double newBalance = balance + amount;
		
		FindNumRange r2 = new FindNumRange(newBalance.intValue());
		
		r2.setRangeStart();
		r2.setRangeEnd();
		
		Prize p = null;
		
		Optional<BankAccount> opBa = bankAccountRepository.findById(accountNum);
		
		BankAccount ba = null;
		
		if(opBa.isPresent()) ba = opBa.get();
		
		Optional<Prize> res = prizeRepository.findById(1);
		
		if(res.isPresent()) p = res.get();
		
		Set<Prize> ownedPrizes = ba.getAllPrizes();
		
		try {
			
			if(newBalance >= 1000 && newBalance < 2000) {	
				
				if(ownedPrizes.contains(p)) return null;
				
				throw new OfferThePrizeException("Prize 1 has been rewarded!!");
				
			}else {
				
				if(r1.getRangeStart() == r2.getRangeStart()) return null;
				
				id = r2.getRangeStart()/1000;
				
				res = prizeRepository.findById(id);
				
				if(res.isPresent()) p = res.get();
				
				if(ownedPrizes.contains(p)) return null;
				
				throw new OfferThePrizeException("Prize "+ id +" has been rewarded!!");
				
			}
			
		}catch(Exception e) {
				
			Set<Prize> prizeSet = new HashSet<>();
				
			ownedPrizes.add(p);
					
			ba.getAllPrizes().addAll(prizeSet);
					
			bankAccountRepository.save(ba);
			
			System.out.println(e.getMessage());
				
			return p;
				
		}
			
		
		
	}
	
	
	
	
	
}

package com.ab.services;

import java.util.Map;
import java.util.Optional;

import com.ab.entities.Prize;
import com.ab.entities.Transaction;

public interface TransactionsService {
	
	public Transaction deposit(Map<String, Object> body);
	
	public Transaction withdrawal(Map<String, Object> body);
	
	public Transaction reversal(String accountNum, Double balance, Double amount, Double odLimit);
	
	public Prize checkEligibleForPrize(Map<String, Object> body);

}

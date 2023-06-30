package com.ab.controllers;

import java.util.Map;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entities.Prize;
import com.ab.entities.Transaction;
import com.ab.services.TransactionsService;

@RestController
public class TransactionsController {
	
	@Autowired
	private TransactionsService transactionsService;
	
	@PostMapping("/deposit")
	public ResponseEntity<Map<String, Object>> deposit(@RequestBody Map<String, Object> body){
		
		Transaction newTransaction = transactionsService.deposit(body);
		
		Prize newPrize = null;
		
		if(body.get("overDraftLimit") == null) newPrize = transactionsService.checkEligibleForPrize(body);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("transaction", newTransaction);
		
		if(newPrize != null) result.put("prize", newPrize);
		
		if(newTransaction != null) return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
		
		return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/withdrawal")
	public ResponseEntity<Transaction> balance(@RequestBody Map<String, Object> body){
	
		Transaction newTransaction = transactionsService.withdrawal(body);
		
		
		if(newTransaction != null) return new ResponseEntity<Transaction>(newTransaction, HttpStatus.CREATED);
		
		return new ResponseEntity<Transaction>(HttpStatus.NO_CONTENT);
	}
	
	
	

}

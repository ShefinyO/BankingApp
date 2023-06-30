package com.ab;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.Transaction;
import com.ab.repositories.BankAccountRepository;
import com.ab.repositories.PrizeRepository;
import com.ab.repositories.TransactionsRepository;
import com.ab.services.TransactionsServiceImpl;

import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
class TransactionTest {

	@Mock
	TransactionsRepository transactionsRepository;
	
	@Mock
	BankAccountRepository bankAccountRepository;
	
	@Mock
	PrizeRepository prizeRepository;
	
	@InjectMocks
	TransactionsServiceImpl transactionsService;
	
	
	@Test
	public void depositCurrentAccountForNewBalanceGreaterThanZero() {
		
		//Arrange
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		LocalDateTime ti = LocalDateTime.now();
		
		LocalDateTime time = ti.withSecond(0).withNano(0);
		
		body.put("accountNumber", "Cur123");
		body.put("balance", 0.0);
		body.put("amount", 100.0);
		body.put("overDraftLimit", 500.0);
		
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		BankAccount ba = new CurrentAccount("Cur123",new HashSet<Transaction>(), 0.0, time, 500.0, 500.0);
		
		Transaction t = new Transaction(ba, "deposit", 100.0, 100.0, time, 500.0);
		
		TransactionMatcher tm = new TransactionMatcher(t);
		
		Transaction tr = new Transaction(1, ba, "deposit", 100.0, 100.0, time, 500.0);
		
		transactions.add(tr);
		
		BankAccount baR = new CurrentAccount("Cur123", transactions, 0.0, time, 500.0, 500.0);
		
		BankAccountMatcher bm = new BankAccountMatcher(baR);
		
		Optional<BankAccount> optionalBankAccount = Optional.of(ba);
		
		Optional<Transaction> optionalTransaction = Optional.of(tr);

		when(bankAccountRepository.findById("Cur123")).thenReturn(optionalBankAccount);
		
		when(transactionsRepository.save(argThat(tm))).thenReturn(tr);
	
		when(bankAccountRepository.save(argThat(bm))).thenReturn(baR);
		
		when(bankAccountRepository.updateBalance(100.0, "Cur123")).thenReturn(1);
		
		when(bankAccountRepository.updateOdBalance(500.0, "Cur123")).thenReturn(1);
		
		when(transactionsRepository.findById(1)).thenReturn(optionalTransaction);
		
		//Act
		
		Transaction actualT = transactionsService.deposit(body);
		
		//Assert
		
		assertEquals(tr, actualT);
		
		
	}
	
	public void depositCurrentAccountForNewBalanceLessThanZero() {
		
		//Arrange
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		LocalDateTime ti = LocalDateTime.now();
		
		LocalDateTime time = ti.withSecond(0).withNano(0);
		
		body.put("accountNumber", "Cur123");
		body.put("balance", -200.0);
		body.put("amount", 100.0);
		body.put("overDraftLimit", 500.0);
		
		Set<Transaction> transactions = new HashSet<Transaction>();
		
		BankAccount ba = new CurrentAccount("Cur123",new HashSet<Transaction>(), -200.0, time, 500.0, 300.0);
		
		Transaction t = new Transaction(ba, "deposit", 100.0, -100.0, time, 400.0);
		
		TransactionMatcher tm = new TransactionMatcher(t);
		
		Transaction tr = new Transaction(1, ba, "deposit", 100.0, -100.0, time, 400.0);
		
		transactions.add(tr);
		
		BankAccount baR = new CurrentAccount("Cur123", transactions, -100.0, time, 500.0, 400.0);
		
		BankAccountMatcher bm = new BankAccountMatcher(baR);
		
		Optional<BankAccount> optionalBankAccount = Optional.of(ba);
		
		Optional<Transaction> optionalTransaction = Optional.of(tr);

		when(bankAccountRepository.findById("Cur123")).thenReturn(optionalBankAccount);
		
		when(transactionsRepository.save(argThat(tm))).thenReturn(tr);
	
		when(bankAccountRepository.save(argThat(bm))).thenReturn(baR);
		
		when(bankAccountRepository.updateBalance(100.0, "Cur123")).thenReturn(1);
		
		when(bankAccountRepository.updateOdBalance(500.0, "Cur123")).thenReturn(1);
		
		when(transactionsRepository.findById(1)).thenReturn(optionalTransaction);
		
		//Act
		
		Transaction actualT = transactionsService.deposit(body);
		
		//Assert
		
		assertEquals(tr, actualT);
		
		
	}
	
}




package com.ab;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.Customer;
import com.ab.entities.SavingsAccount;
import com.ab.repositories.BankAccountRepository;
import com.ab.services.BankAccountService;
import com.ab.services.BankAccountServiceImpl;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

	@Mock
	BankAccountRepository bankAccountRepository;
	
	@InjectMocks
	BankAccountServiceImpl bankAccountService;
	
	
	@Test
	public void createCurrentAccountTest() {
		
		//Arrange
		Customer c = new Customer(1);
		
		CurrentAccount ca = new CurrentAccount(c);
		
		CurrentAccount rca = new CurrentAccount(c);
		
		//number part should be random, Sav remains constant
		rca.setAccountNumber("Cur123");
		
		when(bankAccountRepository.save(ca)).thenReturn(rca);
		
		//Act
		
		BankAccount expectedCa = bankAccountService.createCurrentAccount(c);
		
		//Assert
		
		assertEquals(expectedCa, rca);
		
		
	}
	
	@Test
	public void createSavingsAccountTest() {
		
		//Arrange
		Customer c = new Customer(1);
		
		SavingsAccount sa = new SavingsAccount(c);
		
		SavingsAccount rsa = new SavingsAccount(c);
		
		//Number part should be random, Sav remains constant 
		rsa.setAccountNumber("Sav345");
		
		when(bankAccountRepository.save(sa)).thenReturn(rsa);
		
		//Act
		
		BankAccount expectedSa = bankAccountService.createSavingsAccount(c);
		
		
		//Assert
		
		assertEquals(expectedSa, rsa);
		
		
	}

}

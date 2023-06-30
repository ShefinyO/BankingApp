package com.ab;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ab.entities.Customer;
import com.ab.repositories.CustomerRepository;
import com.ab.services.CustomerRegisterLoginService;
import com.ab.services.CustomerRegisterLoginServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerRegisterLoginTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerRegisterLoginServiceImpl customerRegisterLoginService;
	
	@Test
	public void registerTest() {
		
		//Arrange
		
		Customer c = new Customer("Zamhan", "cheppuus000@gmail.com", "12345");
		
		
		when(customerRepository.save(c)).thenReturn(c);
		
		//Act
		
		Customer cs =  customerRegisterLoginService.registerCustomer(c);
		
		//Assert
		
		assertEquals(c, cs);	
		
	}
	
	@Test
	public void LoginTestValidCredentials(){
		
		//Arrange
		
		Customer c = new Customer("cheppuus000@gmail.com", "12345");
		
		Customer expectedC = new Customer(1,"Shefin","cheppuus000@gmail.com", "12345");
		
		when(customerRepository.checkLoginCredentials(c.getEmail(), c.getPassword())).thenReturn(expectedC);
		
		//Act
		
		Customer actualC = customerRegisterLoginService.loginCustomer(c);
		//Assert
		
		assertEquals(expectedC, actualC);
		
	}
	
	@Test
	public void LoginTestWrongEmail(){
		
		//Arrange
		
		Customer c = new Customer("shammu123@gmail.com", "12345");
		
		Customer expectedC = new Customer(1,"Shefin","cheppuus000@gmail.com", "12345");
		
		when(customerRepository.checkLoginCredentials(c.getEmail(), c.getPassword())).thenReturn(null);
		
		//Act
		
		Customer result = customerRegisterLoginService.loginCustomer(c);
		
		//Assert
		
		assertNull(result);
		
	}
	
	@Test
	public void LoginTestWrongPassword(){
		
		//Arrange
		
		Customer c = new Customer("cheppuus000@gmail.com", "123");
		
		Customer expectedC = new Customer(1,"Shefin","cheppuus000@gmail.com", "12345");
		
		when(customerRepository.checkLoginCredentials(c.getEmail(), c.getPassword())).thenReturn(null);
		
		//Act
		
		Customer result = customerRegisterLoginService.loginCustomer(c);
		
		//Assert
		
		assertNull(result);
		
	}

}

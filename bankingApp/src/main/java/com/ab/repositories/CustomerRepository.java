package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("SELECT c from Customer c WHERE c.email = :email and c.password = :password")
	public Customer checkLoginCredentials(@Param("email") String email, @Param("password") String password);


}

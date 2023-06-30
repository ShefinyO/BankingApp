package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.BankAccount;

import jakarta.transaction.Transactional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String>{
	
	@Modifying
	@Transactional
	@Query("UPDATE BankAccount ba SET ba.balance = :newBalance WHERE ba.accountNumber = :accountNum")
	public int updateBalance(@Param("newBalance") double newBalance, @Param("accountNum") String accountNumber);

		
	@Modifying
	@Transactional
	@Query("UPDATE CurrentAccount ca SET ca.overDraftBalance = :odBal WHERE ca.accountNumber = :accountNum")
	public int updateOdBalance(@Param("odBal") double odBal, @Param("accountNum") String accountNumber);
} 

package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.BankAccount;
import com.ab.entities.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Integer>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Transaction t SET t.bankAccount = :account WHERE t.transactionId = :id")
	public int updateTransactionAccount(@Param("account") BankAccount account, @Param("id") int id);
	

}

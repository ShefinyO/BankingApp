package com.ab;

import org.mockito.ArgumentMatcher;

import com.ab.entities.BankAccount;
import com.ab.entities.Transaction;

public class BankAccountMatcher implements ArgumentMatcher<BankAccount> {
    private final BankAccount expectedTransaction;

    public BankAccountMatcher(BankAccount expectedTransaction) {
        this.expectedTransaction = expectedTransaction;
    }

    @Override
    public boolean matches(BankAccount argument) {
        // Custom logic for matching the relevant fields of Transaction
        return argument.getAccountNumber() == expectedTransaction.getAccountNumber();
        // Add other relevant fields as necessary
    }

	
}
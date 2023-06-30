package com.ab;

import org.mockito.ArgumentMatcher;

import com.ab.entities.Transaction;

public class TransactionMatcher implements ArgumentMatcher<Transaction> {
    private final Transaction expectedTransaction;

    public TransactionMatcher(Transaction expectedTransaction) {
        this.expectedTransaction = expectedTransaction;
    }

    @Override
    public boolean matches(Transaction argument) {
        // Custom logic for matching the relevant fields of Transaction
        return argument.getTransactionId() == expectedTransaction.getTransactionId();
        // Add other relevant fields as necessary
    }

	
}

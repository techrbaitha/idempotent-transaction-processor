package com.interview.transaction.repository;

import com.interview.transaction.model.TransactionRecord;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository {

    private final Map<String, TransactionRecord> transactions =
            new ConcurrentHashMap<>();

    public void save(TransactionRecord transactionRecord) {

        transactions.put(
                transactionRecord.getTransactionRequest().getTransactionId(),
                transactionRecord);
    }

    public TransactionRecord findByTransactionId(String transactionId) {

        return transactions.get(transactionId);
    }

    public boolean exists(String transactionId) {

        return transactions.containsKey(transactionId);
    }

    public Collection<TransactionRecord> findAll() {

        return transactions.values();
    }

}
package com.interview.transaction.service.ordering;

import com.interview.transaction.dto.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PendingTransactionStore {

    private final Map<String, List<TransactionRequest>> pendingTransactions =
            new ConcurrentHashMap<>();

    public void add(TransactionRequest request) {

        pendingTransactions
                .computeIfAbsent(
                        request.getAccountId(),
                        key -> new ArrayList<>())
                .add(request);
    }

    public List<TransactionRequest> getPending(String accountId) {

        return pendingTransactions.getOrDefault(
                accountId,
                new ArrayList<>());
    }

    public void remove(
            String accountId,
            TransactionRequest request) {

        List<TransactionRequest> requests =
                pendingTransactions.get(accountId);

        if (requests != null) {
            requests.remove(request);
        }
    }

}
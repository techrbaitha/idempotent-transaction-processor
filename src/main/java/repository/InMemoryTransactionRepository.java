package repository;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTransactionRepository {
    private final ConcurrentHashMap<String, com.interview.transaction.dto.response.TransactionResponse> transactions =

            new ConcurrentHashMap<>();
}

package com.interview.transaction.repository;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.enums.TransactionStatus;
import com.interview.transaction.model.TransactionRecord;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private final com.interview.transaction.repository.TransactionRepository repository =
            new com.interview.transaction.repository.TransactionRepository();

    @Test
    void shouldSaveTransaction() {

        TransactionRequest request =
                TransactionRequest.builder()
                        .transactionId("TXN-100")
                        .build();

        TransactionRecord record =
                TransactionRecord.builder()
                        .transactionRequest(request)
                        .status(TransactionStatus.PROCESSED)
                        .processedAt(Instant.now())
                        .retryCount(0)
                        .build();

        repository.save(record);

        assertTrue(repository.exists("TXN-100"));
    }

    @Test
    void shouldFindTransaction() {

        TransactionRequest request =
                TransactionRequest.builder()
                        .transactionId("TXN-200")
                        .build();

        TransactionRecord record =
                TransactionRecord.builder()
                        .transactionRequest(request)
                        .status(TransactionStatus.PROCESSED)
                        .processedAt(Instant.now())
                        .retryCount(0)
                        .build();

        repository.save(record);

        assertNotNull(
                repository.findByTransactionId("TXN-200"));
    }

    @Test
    void shouldReturnAllTransactions() {

        TransactionRequest request =
                TransactionRequest.builder()
                        .transactionId("TXN-300")
                        .build();

        repository.save(
                TransactionRecord.builder()
                        .transactionRequest(request)
                        .status(TransactionStatus.PROCESSED)
                        .processedAt(Instant.now())
                        .retryCount(0)
                        .build());

        assertEquals(1,
                repository.findAll().size());
    }
}

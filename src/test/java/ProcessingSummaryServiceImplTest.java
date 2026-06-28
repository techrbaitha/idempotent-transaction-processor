package com.interview.transaction.service.summary;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.dto.response.ProcessingSummary;
import com.interview.transaction.enums.TransactionStatus;
import com.interview.transaction.model.TransactionRecord;
import com.interview.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingSummaryServiceImplTest {

    private TransactionRepository repository;

    private com.interview.transaction.service.summary.ProcessingSummaryServiceImpl summaryService;

    @BeforeEach
    void setup() {

        repository = new TransactionRepository();

        summaryService =
                new com.interview.transaction.service.summary.ProcessingSummaryServiceImpl(repository);
    }

    @Test
    void shouldReturnEmptySummary() {

        ProcessingSummary summary =
                summaryService.getProcessingSummary();

        assertEquals(0,
                summary.getTotalReceived());

        assertEquals(0,
                summary.getProcessed());
    }

    @Test
    void shouldReturnCorrectSummaryCounts() {

        TransactionRequest request =
                TransactionRequest.builder()
                        .transactionId("TXN-1")
                        .build();

        repository.save(

                TransactionRecord.builder()
                        .transactionRequest(request)
                        .status(TransactionStatus.PROCESSED)
                        .processedAt(Instant.now())
                        .retryCount(1)
                        .build());

        ProcessingSummary summary =
                summaryService.getProcessingSummary();

        assertEquals(1,
                summary.getTotalReceived());

        assertEquals(1,
                summary.getProcessed());

        assertEquals(1,
                summary.getRetried());
    }
}
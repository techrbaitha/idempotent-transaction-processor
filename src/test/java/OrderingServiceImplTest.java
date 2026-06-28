package com.interview.transaction.service.ordering;

import com.interview.transaction.dto.request.TransactionRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderingServiceImplTest {

    private final com.interview.transaction.service.ordering.OrderingServiceImpl orderingService =
            new com.interview.transaction.service.ordering.OrderingServiceImpl();

    @Test
    void shouldAcceptFirstTransaction() {

        TransactionRequest request =
                TransactionRequest.builder()
                        .accountId("ACC001")
                        .sequenceNumber(1L)
                        .build();

        assertTrue(orderingService.isInSequence(request));
    }

    @Test
    void shouldAcceptSequentialTransaction() {

        orderingService.isInSequence(
                TransactionRequest.builder()
                        .accountId("ACC001")
                        .sequenceNumber(1L)
                        .build());

        TransactionRequest second =
                TransactionRequest.builder()
                        .accountId("ACC001")
                        .sequenceNumber(2L)
                        .build();

        assertTrue(orderingService.isInSequence(second));
    }

    @Test
    void shouldRejectOutOfOrderTransaction() {

        orderingService.isInSequence(
                TransactionRequest.builder()
                        .accountId("ACC001")
                        .sequenceNumber(1L)
                        .build());

        TransactionRequest request =
                TransactionRequest.builder()
                        .accountId("ACC001")
                        .sequenceNumber(4L)
                        .build();

        assertFalse(orderingService.isInSequence(request));
    }
}
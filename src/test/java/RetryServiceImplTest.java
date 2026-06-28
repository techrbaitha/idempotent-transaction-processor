package com.interview.transaction.service.retry;

import com.interview.transaction.constants.BusinessConstants;
import com.interview.transaction.model.TransactionRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryServiceImplTest {

    private final com.interview.transaction.service.retry.RetryServiceImpl retryService =
            new com.interview.transaction.service.retry.RetryServiceImpl();

    @Test
    void shouldRetryWhenRetryCountIsLessThanMax() {

        TransactionRecord record = TransactionRecord.builder()
                .retryCount(BusinessConstants.MAX_RETRY_COUNT - 1)
                .build();

        assertTrue(retryService.shouldRetry(record));
    }

    @Test
    void shouldNotRetryWhenRetryCountEqualsMax() {

        TransactionRecord record = TransactionRecord.builder()
                .retryCount(BusinessConstants.MAX_RETRY_COUNT)
                .build();

        assertFalse(retryService.shouldRetry(record));
    }

}
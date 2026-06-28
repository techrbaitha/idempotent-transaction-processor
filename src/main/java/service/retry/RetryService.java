package com.interview.transaction.service.retry;

import com.interview.transaction.model.TransactionRecord;

public interface RetryService {

    boolean shouldRetry(TransactionRecord transactionRecord);

}
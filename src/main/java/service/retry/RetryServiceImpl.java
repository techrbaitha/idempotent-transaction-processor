package com.interview.transaction.service.retry;

import com.interview.transaction.constants.BusinessConstants;
import com.interview.transaction.model.TransactionRecord;
import org.springframework.stereotype.Service;

@Service
public class RetryServiceImpl implements com.interview.transaction.service.retry.RetryService {

    @Override
    public boolean shouldRetry(TransactionRecord transactionRecord) {

        return transactionRecord.getRetryCount()
                < BusinessConstants.MAX_RETRY_COUNT;
    }

}
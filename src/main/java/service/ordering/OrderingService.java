package com.interview.transaction.service.ordering;

import com.interview.transaction.dto.request.TransactionRequest;

public interface OrderingService {

    boolean isInSequence(TransactionRequest request);

}
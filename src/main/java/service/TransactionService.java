package com.interview.transaction.service;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.dto.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse processTransaction(
            TransactionRequest request);

}

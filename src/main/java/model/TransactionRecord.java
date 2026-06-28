package com.interview.transaction.model;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {

    private TransactionRequest transactionRequest;

    private TransactionStatus status;

    private Instant processedAt;

    private Integer retryCount;

}
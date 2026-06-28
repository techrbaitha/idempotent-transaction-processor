package com.interview.transaction.dto.response;

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
public class TransactionResponse {

    private String transactionId;

    private String requestId;

    private TransactionStatus status;

    private String message;

    private Instant processedAt;

    private Integer retryCount;

}

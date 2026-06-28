package com.interview.transaction.service.impl;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.dto.response.TransactionResponse;
import com.interview.transaction.enums.TransactionStatus;
import com.interview.transaction.model.TransactionRecord;
import com.interview.transaction.repository.TransactionRepository;
import com.interview.transaction.service.TransactionService;
import com.interview.transaction.validation.TransactionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionValidator validator;

    private final TransactionRepository repository;

    @Override
    public TransactionResponse processTransaction(
            TransactionRequest request) {

        log.info("Processing transaction : {}",
                request.getTransactionId());

        validator.validate(request);

        TransactionRecord existingTransaction =
                repository.findByTransactionId(
                        request.getTransactionId());

        if (existingTransaction != null) {

            log.info(
                    "Duplicate transaction detected : {}",
                    request.getTransactionId());

            return TransactionResponse.builder()
                    .transactionId(request.getTransactionId())
                    .requestId(request.getRequestId())
                    .status(TransactionStatus.DUPLICATE)
                    .message("Duplicate transaction ignored.")
                    .processedAt(existingTransaction.getProcessedAt())
                    .retryCount(existingTransaction.getRetryCount())
                    .build();
        }

        TransactionRecord transactionRecord =
                TransactionRecord.builder()
                        .transactionRequest(request)
                        .status(TransactionStatus.PROCESSED)
                        .processedAt(Instant.now())
                        .retryCount(0)
                        .build();

        repository.save(transactionRecord);

        return TransactionResponse.builder()
                .transactionId(request.getTransactionId())
                .requestId(request.getRequestId())
                .status(TransactionStatus.PROCESSED)
                .message("Transaction processed successfully.")
                .processedAt(transactionRecord.getProcessedAt())
                .retryCount(0)
                .build();
    }

}
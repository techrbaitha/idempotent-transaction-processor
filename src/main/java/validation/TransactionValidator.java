package com.interview.transaction.validation;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.exception.InvalidTransactionException;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidator {

    public void validate(TransactionRequest request) {

        if (request == null) {
            throw new InvalidTransactionException("Transaction request cannot be null.");
        }

        if (request.getTransactionId() == null ||
                request.getTransactionId().isBlank()) {

            throw new InvalidTransactionException("Transaction ID is mandatory.");
        }

        if (request.getRequestId() == null ||
                request.getRequestId().isBlank()) {

            throw new InvalidTransactionException("Request ID is mandatory.");
        }

        if (request.getAccountId() == null ||
                request.getAccountId().isBlank()) {

            throw new InvalidTransactionException("Account ID is mandatory.");
        }

        if (request.getTransactionType() == null) {

            throw new InvalidTransactionException("Transaction type is mandatory.");
        }

        if (request.getAmount() == null ||
                request.getAmount().signum() <= 0) {

            throw new InvalidTransactionException("Amount must be greater than zero.");
        }

        if (request.getCurrency() == null ||
                !request.getCurrency().matches("^[A-Z]{3}$")) {

            throw new InvalidTransactionException(
                    "Currency must be a valid 3-letter ISO code.");
        }

        if (request.getSequenceNumber() == null ||
                request.getSequenceNumber() <= 0) {

            throw new InvalidTransactionException(
                    "Sequence number must be greater than zero.");
        }
    }

}
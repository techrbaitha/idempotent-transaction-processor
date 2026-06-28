package com.interview.transaction.enums;

public enum TransactionStatus {

    RECEIVED,

    VALIDATED,

    PROCESSING,

    PROCESSED,

    DUPLICATE,

    RETRY_PENDING,

    FAILED,

    INVALID

}
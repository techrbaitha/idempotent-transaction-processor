package com.interview.transaction.dto.request;

import com.interview.transaction.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotBlank(message = "Transaction Id is mandatory")
    private String transactionId;

    @NotBlank(message = "Request Id is mandatory")
    private String requestId;

    @NotBlank(message = "Account Id is mandatory")
    private String accountId;

    @NotNull(message = "Transaction Type is mandatory")
    private TransactionType transactionType;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Currency is mandatory")
    private String currency;

    @NotNull(message = "Sequence Number is mandatory")
    private Long sequenceNumber;

}
package com.interview.transaction.controller;

import com.interview.transaction.dto.request.TransactionRequest;
import com.interview.transaction.dto.response.TransactionResponse;
import com.interview.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/process")
    public TransactionResponse processTransaction(
            @Valid @RequestBody
            TransactionRequest request) {

        return transactionService.processTransaction(request);
    }

}
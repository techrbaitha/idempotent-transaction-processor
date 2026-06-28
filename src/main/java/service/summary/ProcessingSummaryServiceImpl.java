package com.interview.transaction.service.summary;

import com.interview.transaction.dto.response.ProcessingSummary;
import com.interview.transaction.enums.TransactionStatus;
import com.interview.transaction.model.TransactionRecord;
import com.interview.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProcessingSummaryServiceImpl implements com.interview.transaction.service.summary.ProcessingSummaryService {

    private final TransactionRepository repository;

    @Override
    public ProcessingSummary getProcessingSummary() {

        Collection<TransactionRecord> transactions =
                repository.findAll();

        int totalReceived = transactions.size();

        int processed = 0;
        int duplicates = 0;
        int failed = 0;
        int pending = 0;
        int retried = 0;

        for (TransactionRecord transaction : transactions) {

            TransactionStatus status = transaction.getStatus();

            switch (status) {

                case PROCESSED -> processed++;

                case DUPLICATE -> duplicates++;

                case FAILED -> failed++;

                case RECEIVED,
                     PROCESSING,
                     VALIDATED,
                     RETRY_PENDING -> pending++;

                default -> {
                }
            }

            if (transaction.getRetryCount() != null
                    && transaction.getRetryCount() > 0) {

                retried++;
            }
        }

        return ProcessingSummary.builder()
                .totalReceived(totalReceived)
                .processed(processed)
                .duplicates(duplicates)
                .failed(failed)
                .pending(pending)
                .retried(retried)
                .build();
    }

}

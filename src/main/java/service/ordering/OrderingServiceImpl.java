package com.interview.transaction.service.ordering;

import com.interview.transaction.dto.request.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderingServiceImpl implements OrderingService {

    private final Map<String, Long> latestProcessedSequence =
            new ConcurrentHashMap<>();

    @Override
    public boolean isInSequence(TransactionRequest request) {

        Long lastProcessed =
                latestProcessedSequence.get(request.getAccountId());

        if (lastProcessed == null) {

            latestProcessedSequence.put(
                    request.getAccountId(),
                    request.getSequenceNumber());

            return true;
        }

        if (request.getSequenceNumber() == lastProcessed + 1) {

            latestProcessedSequence.put(
                    request.getAccountId(),
                    request.getSequenceNumber());

            return true;
        }

        return false;
    }

}

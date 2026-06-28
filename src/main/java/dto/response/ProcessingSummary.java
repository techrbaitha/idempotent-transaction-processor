package com.interview.transaction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingSummary {

    private Integer totalReceived;

    private Integer processed;

    private Integer duplicates;

    private Integer failed;

    private Integer pending;

    private Integer retried;

}
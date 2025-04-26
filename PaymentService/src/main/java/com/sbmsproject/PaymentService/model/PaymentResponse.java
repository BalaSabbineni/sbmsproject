package com.sbmsproject.PaymentService.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PaymentResponse {

    private long paymentId;
    private long amount;
    private String status;
    private PaymentMode paymentMode;
    private Instant paymentDate;
    private long orderId;
}

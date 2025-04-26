package com.sbmsproject.order_service.external.client.response;

import com.sbmsproject.order_service.model.PaymentMode;
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
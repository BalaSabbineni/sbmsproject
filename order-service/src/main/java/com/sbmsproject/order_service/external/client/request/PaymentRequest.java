package com.sbmsproject.order_service.external.client.request;

import com.sbmsproject.order_service.model.PaymentMode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;

}
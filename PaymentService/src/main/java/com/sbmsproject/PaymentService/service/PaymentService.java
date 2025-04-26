package com.sbmsproject.PaymentService.service;

import com.sbmsproject.PaymentService.model.PaymentRequest;
import com.sbmsproject.PaymentService.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}

package com.sbmsproject.PaymentService.service;

import com.sbmsproject.PaymentService.entity.TransactionDetails;
import com.sbmsproject.PaymentService.model.PaymentRequest;
import com.sbmsproject.PaymentService.model.PaymentResponse;
import com.sbmsproject.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    public PaymentServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {}", paymentRequest);

        TransactionDetails details = TransactionDetails.builder()
                .amount(paymentRequest.getAmount())
                .paymentDate(Instant.now())
                .paymentMode(String.valueOf(paymentRequest.getPaymentMode()))
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetailsRepository.save(details);

        log.info("Transaction Completed with Id: {}", details.getId());
        return details.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        TransactionDetails details = transactionDetailsRepository.findByOrderId(orderId);

        return PaymentResponse.builder()
                .orderId(details.getOrderId())
                .paymentDate(details.getPaymentDate())
                .amount(details.getAmount())
                .build();
    }
}

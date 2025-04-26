package com.sbmsproject.PaymentService.controller;

import com.sbmsproject.PaymentService.model.PaymentRequest;
import com.sbmsproject.PaymentService.model.PaymentResponse;
import com.sbmsproject.PaymentService.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long doPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.doPayment(paymentRequest);
    }

    @GetMapping("/{orderId}")
    public PaymentResponse getPaymentDetailsByOrderId(@PathVariable long orderId) {
        return paymentService.getPaymentDetailsByOrderId(orderId);

    }
}

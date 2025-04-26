package com.sbmsproject.order_service.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import com.sbmsproject.order_service.external.client.request.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "payment-service", url = "http://payment-service:8081")
public interface PaymentServiceClient {

    @PostMapping("/payment")
    public Long doPayment(@RequestBody PaymentRequest paymentRequest);

    //default method in Interface
    default void fallback(Exception e) {
        throw new RuntimeException("PaymentService down");
    }
}

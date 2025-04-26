package com.sbmsproject.API_Gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/get")
    public String get() {
        return "This is API GW Service .....!";
    }

    @GetMapping("/orderServiceFallback")
    public String orderServiceFallback() {
        return "orderServiceFallback";
    }

    @GetMapping("/paymentServiceFallback")
    public String paymentServiceFallback() {
        return "paymentServiceFallback";
    }

    @GetMapping("/productServiceFallback")
    public String productServiceFallback() {
        return "productServiceFallback";
    }
}

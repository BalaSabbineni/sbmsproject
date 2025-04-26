package com.sbmsproject.order_service.controller;

import com.sbmsproject.order_service.model.OrderRequest;
import com.sbmsproject.order_service.model.OrderResponse;
import com.sbmsproject.order_service.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable long orderId) {
        return orderService.getOrderDetails(orderId);
    }
}

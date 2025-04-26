package com.sbmsproject.order_service.service;

import com.sbmsproject.order_service.external.client.request.PaymentRequest;
import com.sbmsproject.order_service.entity.Order;
import com.sbmsproject.order_service.external.client.PaymentServiceClient;
import com.sbmsproject.order_service.external.client.ProductServiceClient;
import com.sbmsproject.order_service.model.OrderRequest;
import com.sbmsproject.order_service.model.OrderResponse;
import com.sbmsproject.order_service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productService;
    private final PaymentServiceClient paymentServiceClient;
    private final RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceClient productService, PaymentServiceClient paymentServiceClient, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.paymentServiceClient = paymentServiceClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public long placeOrder(OrderRequest request) {
        //Create Order
        log.info("placing order request: {}", request);
        productService.reduceQuantity(request.getProductId(), request.getQuantity());

        log.info("Creating order request");
        Order order = Order.builder()
                .amount(request.getTotalAmount())
                .orderStatus("Created")
                .orderDate(Instant.now())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();
        log.info("Saving order request");
        order = orderRepository.save(order);

        log.info("Calling Payment service");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(request.getPaymentMode())
                .amount(request.getTotalAmount())
                .build();

        log.info("calling  Payment service using feign client, payment request: {}", paymentRequest.toString());
        paymentServiceClient.doPayment(paymentRequest);

        order.setOrderStatus("Success"); // add try catch if payment failed
        orderRepository.save(order);

        log.info("order created with Id: {}", order.getId());

        // next order product quantity by calling product API to reduce quantity
        // Payment service : payment success or not


        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with Id: " + id));

        log.info("Calling product service to fetch product for order using rest template");
        log.info("product service url: {}", "http://product-service:8080/product/" + order.getProductId());

        OrderResponse.ProductDetails productDetails = restTemplate.getForObject(
                "http://product-service:8080/product/" + order.getProductId(),
                OrderResponse.ProductDetails.class
        );
        log.info("Product details: {}", productDetails);

        OrderResponse.PaymentDetails paymentDetails = restTemplate.getForObject(
                "http://payment-service:8081/payment/" + order.getId(),
                OrderResponse.PaymentDetails.class
        );
        log.info("Payment response: {}", paymentDetails);

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}

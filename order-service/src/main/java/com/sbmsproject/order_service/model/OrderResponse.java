package com.sbmsproject.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sbmsproject.order_service.model.PaymentMode;

import java.time.Instant;

@Data
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails {
        private long productId;
        private String productName;
        private long productPrice;
        private long productQuantity;
    }

    @Data
    @Builder
    public static class PaymentDetails {

        private long paymentId;
        private long amount;
        private String status;
        private PaymentMode paymentMode;
        private Instant paymentDate;
        private long orderId;
    }
}

package com.sbmsproject.order_service.service;

import com.sbmsproject.order_service.model.OrderRequest;
import com.sbmsproject.order_service.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest request);

    OrderResponse getOrderDetails(long orderId);
}

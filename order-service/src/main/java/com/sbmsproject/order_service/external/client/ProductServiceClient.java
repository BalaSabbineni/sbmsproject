package com.sbmsproject.order_service.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "product-service", url = "http://product-service:8080")
public interface ProductServiceClient {
    //value ="productServiceClient", url="http://localhost:8080"
    // (use this If you didn't added eureka.instance.hostname=localhost in eureka client properties)

    @PutMapping("/product/reduceQuantity/{id}")
    public void reduceQuantity(@PathVariable("id") long productId,
                               @RequestParam long quantity);

    default void fallback(Exception e) {
        throw new RuntimeException("PRODUCT service down");
    }
}

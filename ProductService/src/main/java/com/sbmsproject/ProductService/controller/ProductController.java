package com.sbmsproject.ProductService.controller;

import com.sbmsproject.ProductService.entity.Product;
import com.sbmsproject.ProductService.model.ProductRequest;
import com.sbmsproject.ProductService.model.ProductResponse;
import com.sbmsproject.ProductService.service.ProductService;
import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable("id") long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/reduceQuantity/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void reduceQuantity(@PathVariable("id") long productId,
                               @RequestParam long quantity) {
        productService.redueceQuantity(productId, quantity);

    }
}

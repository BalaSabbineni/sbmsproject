package com.sbmsproject.ProductService.service;

import com.sbmsproject.ProductService.entity.Product;
import com.sbmsproject.ProductService.model.ProductRequest;

import java.util.List;

import com.sbmsproject.ProductService.model.ProductResponse;


public interface ProductService {
    long addProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse getProductById(long id);

    void redueceQuantity(long productId, long quantity);
}

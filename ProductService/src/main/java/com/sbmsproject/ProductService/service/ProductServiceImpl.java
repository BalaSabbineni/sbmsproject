package com.sbmsproject.ProductService.service;

import com.sbmsproject.ProductService.entity.Product;
import com.sbmsproject.ProductService.exception.ProductServiceCustomException;
import com.sbmsproject.ProductService.model.ProductRequest;
import com.sbmsproject.ProductService.model.ProductResponse;
import com.sbmsproject.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity()).build();
        productRepository.save(product);

        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public List<ProductResponse> getProducts() {
        return List.of();
    }

    @Override
    public ProductResponse getProductById(long id) {
        log.info("Get product for productId: {}", id);
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found", "404"));

//        ProductResponse productResponse = new ProductResponse();
//        BeanUtils.copyProperties(product, productResponse); // we can use bean match response if properties are same

        return new ProductResponse(product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getQuantity());
    }

    @Override
    public void redueceQuantity(long productId, long quantity) {
        log.info("reduce quantity {} for Id {}", quantity, productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException("Prodcut not found", ""));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("product quantity not have sufficient quantity");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("product quantity updated");
    }
}

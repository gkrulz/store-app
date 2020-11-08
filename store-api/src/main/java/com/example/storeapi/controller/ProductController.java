package com.example.storeapi.controller;

import com.example.storeapi.beans.OrderProduct;
import com.example.storeapi.beans.Product;
import com.example.storeapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {

        logger.info("[Product] Request received to retrieve all products");

        return productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {

        logger.info("[Product] Request received product with ID: {}", productId);

        return productService.getProduct(productId);
    }

    @PostMapping("/order")
    public List<OrderProduct> createOrder(@RequestBody List<OrderProduct> order) {

        logger.info("[Order] Request received to create order");

        return productService.createOrder(order);
    }

}

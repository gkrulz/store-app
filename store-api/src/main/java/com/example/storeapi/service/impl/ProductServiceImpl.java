package com.example.storeapi.service.impl;

import com.example.storeapi.beans.OrderProduct;
import com.example.storeapi.beans.Product;
import com.example.storeapi.repository.ProductRepository;
import com.example.storeapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    public static final double MANUAL_LABOR = 1.3;

    @Override
    public List<Product> getProducts() {
        logger.info("[Product] Retrieving all products");

        // Get products from DB
        return productRepository.getProducts();
    }

    @Override
    public Product getProduct(String productId) {
        logger.info("[Product] Retrieving products with product ID: {}", productId);

        // Validate Product ID
        if (Objects.isNull(productId)) {
            String error = "Product ID Not Found";
            logger.error(error);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error);
        }

        // Get the product from DB
        return productRepository.getProduct(productId);
    }

    @Override
    public List<OrderProduct> createOrder(List<OrderProduct> order) {
        logger.info("[Order] Creating Order");

        // Get price for each products
        for(OrderProduct orderProduct : order) {

            if (Objects.isNull(orderProduct.getProductId())) {
                String error = "Product ID not found. ";
                logger.error(error);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, error);
            }

            // Get product info
            Product product = getProduct(orderProduct.getProductId());

            int noOfCartons;
            int noOfSingleProducts;
            double priceOfCartons;
            double priceOfSingleProducts;
            double discount = 0;

            noOfCartons = orderProduct.getOrderSize() / product.getCartonSize();
            noOfSingleProducts = orderProduct.getOrderSize() % product.getCartonSize();

            // Discounts
            if (noOfCartons >= 3) {
                discount = 0.1;
            }

            priceOfCartons = noOfCartons * (product.getPrice() * (1 - discount));

            double priceOfSingleProduct = product.getPrice() / product.getCartonSize();
            priceOfSingleProduct = priceOfSingleProduct * MANUAL_LABOR;
            priceOfSingleProducts = noOfSingleProducts * priceOfSingleProduct;

            orderProduct.setPrice(priceOfCartons + priceOfSingleProducts);
        }

        return order;
    }
}

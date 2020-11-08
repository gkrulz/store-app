package com.example.storeapi.service;

import com.example.storeapi.beans.Order;
import com.example.storeapi.beans.OrderProduct;
import com.example.storeapi.beans.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProduct(String productId);

    List<OrderProduct> createOrder(List<OrderProduct> order);
}

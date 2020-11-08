package com.example.storeapi.repository;

import com.example.storeapi.beans.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getProducts();

    Product getProduct(String productId);
}

package com.example.storeapi.beans;

import java.util.List;
import java.util.Objects;

public class Order {

    private List<OrderProduct> products;
    private double total;

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.getTotal(), getTotal()) == 0 &&
                Objects.equals(getProducts(), order.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProducts(), getTotal());
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", total=" + total +
                '}';
    }
}

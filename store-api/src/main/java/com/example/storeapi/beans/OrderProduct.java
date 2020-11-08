package com.example.storeapi.beans;

import java.util.Objects;

public class OrderProduct {
    private String productId;
    private int orderSize;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return getOrderSize() == that.getOrderSize() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getOrderSize(), getPrice());
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productId='" + productId + '\'' +
                ", orderSize=" + orderSize +
                ", price=" + price +
                '}';
    }
}

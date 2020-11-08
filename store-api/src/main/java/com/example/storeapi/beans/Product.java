package com.example.storeapi.beans;

import java.util.Objects;

public class Product {

    public String productId;
    private String name;
    private double price;
    private int cartonSize;
    private String imageUrl;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(int cartonSize) {
        this.cartonSize = cartonSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getCartonSize() == product.getCartonSize() &&
                Objects.equals(getProductId(), product.getProductId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getImageUrl(), product.getImageUrl()) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getPrice(), getCartonSize(), getImageUrl(), getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cartonSize=" + cartonSize +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

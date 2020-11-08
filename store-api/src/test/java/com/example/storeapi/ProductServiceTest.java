package com.example.storeapi;

import com.example.storeapi.beans.OrderProduct;
import com.example.storeapi.beans.Product;
import com.example.storeapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProducts() {
        List<Product> products = productService.getProducts();
        assertEquals(products.size(), 2);
    }

    @Test
    public void testGetProduct() {
        String productId = "9780601a-c484-4f9d-89c4-8a5a21c1bf11";
        Product product = productService.getProduct(productId);
        assertEquals(productId, product.getProductId());
    }

    @Test
    public void testCreateOrder() {
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProductId("9780601a-c484-4f9d-89c4-8a5a21c1bf11");
        orderProduct1.setOrderSize(25);

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setProductId("e1893580-b3bd-464c-b63b-b1ae45650825");
        orderProduct2.setOrderSize(8);

        List<OrderProduct> order = new ArrayList<>();
        order.add(orderProduct1);
        order.add(orderProduct2);

        List<OrderProduct> pricedOrder = productService.createOrder(order);
        for (OrderProduct orderProduct : pricedOrder) {
            if (orderProduct.getProductId().equals("9780601a-c484-4f9d-89c4-8a5a21c1bf11")) {
                assertEquals(231.875, orderProduct.getPrice());
            }
            if (orderProduct.getProductId().equals("e1893580-b3bd-464c-b63b-b1ae45650825")) {
                assertEquals(1468.5, orderProduct.getPrice());
            }
        }
    }

    @Test
    public void testCreateOrderWithOnlySingleItems() {
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProductId("9780601a-c484-4f9d-89c4-8a5a21c1bf11");
        orderProduct1.setOrderSize(3);

        List<OrderProduct> order = new ArrayList<>();
        order.add(orderProduct1);

        List<OrderProduct> pricedOrder = productService.createOrder(order);

        if (order.get(0).getProductId().equals("9780601a-c484-4f9d-89c4-8a5a21c1bf11")) {
            assertEquals(34.125 , order.get(0).getPrice());
        }
    }

    @Test
    public void testCreateOrderWithMoreThanThreeCartons() {
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProductId("9780601a-c484-4f9d-89c4-8a5a21c1bf11");
        orderProduct1.setOrderSize(61);

        List<OrderProduct> order = new ArrayList<>();
        order.add(orderProduct1);

        List<OrderProduct> pricedOrder = productService.createOrder(order);

        if (order.get(0).getProductId().equals("9780601a-c484-4f9d-89c4-8a5a21c1bf11")) {
            assertEquals(483.875 , order.get(0).getPrice());
        }
    }
}

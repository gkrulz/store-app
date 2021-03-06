package com.example.storeapi.repository.Impl;

import com.example.storeapi.beans.Product;
import com.example.storeapi.repository.ProductRepository;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private PGSimpleDataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Override
    public List<Product> getProducts() {

        logger.info("[Product] Retrieve all products");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Product> results = new ArrayList<>();
        try {
            String query = "SELECT * FROM \"PRODUCT\"";
            results = namedParameterJdbcTemplate.query(query,
                    parameters,
                    (resultSet, rowNum) -> {
                        Product t = new Product();
                        t.setProductId(resultSet.getString("PRODUCT_ID"));
                        t.setName(resultSet.getString("PRODUCT_NAME"));
                        t.setPrice(resultSet.getDouble("PRODUCT_PRICE"));
                        t.setCartonSize(resultSet.getInt("PRODUCT_CARTON_SIZE"));
                        t.setImageUrl(resultSet.getString("PRODUCT_IMAGE_URL"));
                        t.setDescription(resultSet.getString("PRODUCT_DESCRIPTION"));
                        return t;
                    });
        } catch (Exception e) {
            String error = "[Product] Failed to retrieve the products ";
            logger.error(error, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, error);
        }

        return results;
    }

    public Product getProduct(String productId) {

        logger.info("[Product] Retrieve product with product ID: {}", productId);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("productId", productId);

        List<Product> results = new ArrayList<>();
        try {
            String query = "SELECT * FROM \"PRODUCT\" WHERE \"PRODUCT_ID\"=:productId";
            results = namedParameterJdbcTemplate.query(query,
                    parameters,
                    (resultSet, rowNum) -> {
                        Product t = new Product();
                        t.setProductId(resultSet.getString("PRODUCT_ID"));
                        t.setName(resultSet.getString("PRODUCT_NAME"));
                        t.setPrice(resultSet.getDouble("PRODUCT_PRICE"));
                        t.setCartonSize(resultSet.getInt("PRODUCT_CARTON_SIZE"));
                        t.setImageUrl(resultSet.getString("PRODUCT_IMAGE_URL"));
                        t.setDescription(resultSet.getString("PRODUCT_DESCRIPTION"));
                        return t;
                    });
        } catch (Exception e) {
            String error = "[Product] Failed to retrieve the product ";
            logger.error(error, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, error);
        }

        if (results.size() == 0) {
            String error = "Product Not Found";
            logger.error(error);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error);
        }

        return results.get(0);
    }
}

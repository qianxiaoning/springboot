package com.qxn.service;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;

import java.util.List;

public interface ProductService {
    Product findProductById(Long productId);

    ProductDesc findProductDescById(Long productId);

    List<Product> findProducts(Integer page, Integer rows);
}

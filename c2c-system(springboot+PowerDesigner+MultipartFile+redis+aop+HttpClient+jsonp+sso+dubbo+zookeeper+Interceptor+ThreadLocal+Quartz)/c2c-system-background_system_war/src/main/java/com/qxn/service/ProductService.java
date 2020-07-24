package com.qxn.service;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.vo.UITable;

import java.util.List;

public interface ProductService {

    UITable findProductByPage(Integer page, Integer rows);

    void saveProduct(Product product, ProductDesc productDesc);

    void updateProduct(Product product, ProductDesc productDesc);

    void deleteProduct(Long[] ids);

    void updateProductStatus(int status, String ids);

    ProductDesc findProductDescById(Long id);

    Product findProductById(Long id);

    List<Product> findProducts(Integer page, Integer rows);
}

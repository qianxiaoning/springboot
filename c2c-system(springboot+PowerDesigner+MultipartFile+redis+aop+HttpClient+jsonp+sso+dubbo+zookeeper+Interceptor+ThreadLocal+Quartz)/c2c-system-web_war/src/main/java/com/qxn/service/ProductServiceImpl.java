package com.qxn.service;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.util.HttpClientService;
import com.qxn.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private HttpClientService httpClient;
    private String backgroundSystemUrl = "http://manage.c2c-system.com/api/";

    @Override
    public Product findProductById(Long productId) {
        //因为用了nginx代理，此处加/api
        String url = backgroundSystemUrl + "web/product/findProductById";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", productId + "");
        String result = httpClient.doGet(url, params);
        return ObjectMapperUtil.toObject(result, Product.class);
    }

    @Override
    public ProductDesc findProductDescById(Long productId) {
        //因为用了nginx代理，此处加/api
        String url = backgroundSystemUrl + "web/product/findProductDescById";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", productId + "");
        String result = httpClient.doGet(url, params);
        return ObjectMapperUtil.toObject(result, ProductDesc.class);
    }

    @Override
    public List<Product> findProducts(Integer page, Integer rows) {
        //因为用了nginx代理，此处加/api
        String url = backgroundSystemUrl + "web/product/findProducts";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("page", page + "");
        params.put("rows", rows + "");
        String result = httpClient.doGet(url, params);
        return ObjectMapperUtil.toObject(result, List.class);
    }
}

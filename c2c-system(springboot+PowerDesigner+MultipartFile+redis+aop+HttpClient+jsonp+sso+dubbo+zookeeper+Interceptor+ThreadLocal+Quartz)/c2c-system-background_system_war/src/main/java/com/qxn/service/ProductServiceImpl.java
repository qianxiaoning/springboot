package com.qxn.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qxn.mapper.ProductDescMapper;
import com.qxn.mapper.ProductMapper;
import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.vo.ProductAndCategoryName;
import com.qxn.vo.UITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDescMapper productDescMapper;

    @Override
    public UITable findProductByPage(Integer page, Integer rows) {
        Integer total = productMapper.selectCount(null);
        int start = (page - 1) * rows;
        List<ProductAndCategoryName> productList = productMapper.findProductByPage(start, rows);
        UITable table = new UITable(total, productList);
        return table;
    }

    @Override
    @Transactional
    public void saveProduct(Product product, ProductDesc productDesc) {
        product.setStatus(1)
                .setCreated(new Date())
                .setUpdated(product.getCreated());
        productMapper.insert(product);
        //mybatis特性，product.id主键自增后回填主键信息
        //新增详情
        productDesc.setItemId(product.getId())
                .setCreated(product.getCreated())
                .setUpdated(product.getCreated());
        productDescMapper.insert(productDesc);
    }

    @Override
    @Transactional
    public void updateProduct(Product product, ProductDesc productDesc) {
        product.setUpdated(new Date());
        productMapper.updateById(product);
        productDesc.setUpdated(product.getUpdated());
        productDescMapper.updateById(productDesc);
    }

    @Override
    @Transactional
    public void deleteProduct(Long[] ids) {
        //数组转集合
        List<Long> idList = Arrays.asList(ids);
        productMapper.deleteBatchIds(idList);
        productDescMapper.deleteBatchIds(idList);
    }

    @Override
    public void updateProductStatus(int status, String ids) {
        String[] arr = ids.split(",");
        for (String id : arr) {
            Long longId = Long.valueOf(id);
            Product product = new Product();
            product.setStatus(status)
                    .setId(longId)
                    .setUpdated(new Date());
            productMapper.updateById(product);
        }
    }

    @Override
    public ProductDesc findProductDescById(Long id) {
        return productDescMapper.selectById(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> findProducts(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        List<Product> productList = productMapper.findProducts(start, rows);
        return productList;
    }
}

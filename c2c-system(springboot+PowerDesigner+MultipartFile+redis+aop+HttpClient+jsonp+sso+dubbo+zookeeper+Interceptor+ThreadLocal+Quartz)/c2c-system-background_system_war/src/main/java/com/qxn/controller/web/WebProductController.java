package com.qxn.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/web/product")
public class WebProductController {
	@Autowired
	private ProductService ProductService;
	/**
	 *编辑c2c-manage 完成数据获取
	 *findProductById?id=1
	 */
	@GetMapping("/findProductById")
	public Product findProductById(Long id) {
		return ProductService.findProductById(id);
	}
	@GetMapping("/findProductDescById")
	public ProductDesc findProductDescById(Long id) {
		return ProductService.findProductDescById(id);
	}
	@GetMapping("/findProducts")
	public List<Product> findProducts(Integer page, Integer rows) {
		return ProductService.findProducts(page,rows);
	}
}

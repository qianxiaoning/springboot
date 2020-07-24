package com.qxn.controller;

import com.qxn.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.service.ProductService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService ProductService;
	/**
	 * 根据页面url请求，跳转通用的商品展现页面
	 */
	@GetMapping("/{productId}")
	public SysResult findProductById(@PathVariable Long productId) {
		Product product = ProductService.findProductById(productId);
		ProductDesc productDesc = ProductService.findProductDescById(productId);
		HashMap<String, Object> map = new HashMap<>();
		map.put("product",product);
		map.put("productDesc",productDesc);
		return SysResult.success(map);
	}
	@GetMapping("")
	public SysResult findProducts() {
		List<Product> list = ProductService.findProducts(1,10);
		return SysResult.success(list);
	}
}

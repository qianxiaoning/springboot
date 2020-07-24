package com.qxn.controller;

import com.qxn.pojo.ProductCategory;
import com.qxn.service.ProductCategoryService;
import com.qxn.vo.SysResult;
import com.qxn.vo.UITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	@GetMapping("/queryById")//未用
	public SysResult findProductCategoryById(Long productCategoryId){
		ProductCategory productCategory = productCategoryService.findProductCategoryById(productCategoryId);
		return SysResult.success(productCategory.getName());
	}
	@GetMapping("/list")
	/*controller中，@RequestParam注解，
	(@RequestParam(value = "id",defaultValue = "0",required = false) Long parentId){}
	表示，前端传入id，后台转为parentId接收，默认值为0
	* */
	public SysResult findProductCategoryList(@RequestParam(value = "id",defaultValue = "0",required = false) Long parentId){
		List<UITree> list = productCategoryService.findUITreeList(parentId);
		//用加上缓存的方法
//		List<UITree> list = productCategoryService.findUITreeCacheList(parentId);
		return SysResult.success(list);
	}

	
}

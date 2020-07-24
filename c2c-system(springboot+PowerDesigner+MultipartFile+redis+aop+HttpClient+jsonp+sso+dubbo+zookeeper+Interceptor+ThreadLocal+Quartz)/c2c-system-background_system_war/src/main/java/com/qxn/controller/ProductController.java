package com.qxn.controller;

import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.service.ProductService;
import com.qxn.vo.ProductAndDesc;
import com.qxn.vo.SysResult;
import com.qxn.vo.UITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/query")
	public SysResult findProductByPage(Integer page, Integer rows){
		UITable uITable = productService.findProductByPage(page,rows);
		return SysResult.success(uITable);
	}
	@PostMapping("/save")
	public SysResult saveProduct(@RequestBody ProductAndDesc productAndDesc){
		productService.saveProduct(productAndDesc.getProduct(),productAndDesc.getProductDesc());
		return SysResult.success();
	}
	@PutMapping("/update")
	public SysResult updateProduct(@RequestBody ProductAndDesc productAndDesc){
		productService.updateProduct(productAndDesc.getProduct(),productAndDesc.getProductDesc());
		return SysResult.success();
	}
	@DeleteMapping("/delete")
	//ids:"1,2,3",在controller中用Long[]接收，会自动拆分成[1l,2l,3l]
	public SysResult deleteProduct(Long[] ids){
		productService.deleteProduct(ids);
		return SysResult.success();
	}
	//下架
	@PutMapping("/instock")
	public SysResult instockProduct(@RequestBody String ids){
		int status = 2;//2为下架
		productService.updateProductStatus(status,ids);
		return SysResult.success();
	}
	//上架
	@PutMapping("/reshelf")
	public SysResult reshelfProduct(@RequestBody String ids){
		int status = 1;//1为上架
		productService.updateProductStatus(status,ids);
		return SysResult.success();
	}
	//restful风格接收参数，@PathVariable注解取参数
	@GetMapping("/query/product/desc/{id}")
	public SysResult findProductDescById(@PathVariable Long id){
		ProductDesc productDesc = productService.findProductDescById(id);
		return SysResult.success(productDesc);
	}

}

package com.qxn.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qxn.dubbo.service.DubboCartService;
import com.qxn.pojo.Cart;
import com.qxn.pojo.User;
import com.qxn.util.ThreadLocalUtil;
import com.qxn.vo.SysResult;

@RestController
@RequestMapping("/cart")
public class CartController {
	private static final String C2CSYSTEM_USER = "C2CSYSTEM_USER";
	@Reference(check=false)
	private DubboCartService cartService;
	/**
	 * item="${cartList}"
	 *查询用户的全部购物记录信息 
	 */
	@GetMapping("/queryCartByUserId")
	public SysResult queryCartByUserId(HttpServletRequest request){
//		User user = (User)request.getAttribute(C2CSYSTEM_USER);
//		Long userId = user.getId();
		Long userId = ThreadLocalUtil.get().getId();
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		return SysResult.success(cartList);
	}
	@PutMapping("/update/num/{itemId}/{num}")
	public SysResult updateCartNum(Cart cart){
		Long userId = ThreadLocalUtil.get().getId();
//		Long userId = 7l;
		cart.setUserId(userId);
		cartService.updateCartNum(cart);
		return SysResult.success();
	}
	@DeleteMapping("/delete/{itemId}")
	public SysResult deleteCart(Cart cart){
		Long userId = ThreadLocalUtil.get().getId();
//		Long userId = 7l;
		cart.setUserId(userId);
		cartService.deleteCart(cart);
		return SysResult.success("删除成功");
	}
	@PostMapping("/add/{itemId}")
	public SysResult addItem(@RequestBody Cart cart){
		System.out.println(cart);
		Long userId = ThreadLocalUtil.get().getId();
//		Long userId = 7l;
		cart.setUserId(userId);
		cartService.addItem(cart);
		return SysResult.success();
	}
}

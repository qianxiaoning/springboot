package com.qxn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qxn.dubbo.service.DubboCartService;
import com.qxn.pojo.Cart;
import com.qxn.dubbo.service.DubboOrderService;
import com.qxn.pojo.Order;
import com.qxn.pojo.OrderShipping;
import com.qxn.util.ThreadLocalUtil;
import com.qxn.vo.SysResult;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Reference(check=false)
	private DubboCartService cartService;
	@Reference(check=false)
	private DubboOrderService orderService;

	@RequestMapping("/create")
	public SysResult create() {
		Long userId = ThreadLocalUtil.get().getId();
		List<Cart> carts = cartService.findCartListByUserId(userId);
		return SysResult.success(carts);
	}
	/**
	 *业务：
	 *完成订单入库，并返回orderId
	 *自己动态生成一个orderId uuid
	 *同时实现3张表入库的操作
	 *事务控制 
	 */
	@PostMapping("/submit")
	public SysResult saveOrder(@RequestBody Order order) {
		if(order == null || order.getOrderShipping() == null ||
		order.getOrderItems() == null ||
		order.getOrderItems().size() == 0) {
			return SysResult.fail("数据提交不正确");
		}
		Long userId = ThreadLocalUtil.get().getId();
		order.setUserId(userId);
		String orderId = orderService.saveOrder(order);
		return SysResult.success(orderId);
	}
	//根据orderId查询数据库 3张表查询 之后页面展现数据
	//"/order/success?id="xxx"
	@GetMapping("/success")
	public SysResult queryOrderByIdMybatisPlus(String id) {
//		Order order = orderService.queryOrderByIdMybatisPlus(id);
		Order order = orderService.queryOrderByIdSql(id);
		return SysResult.success(order);
	}
}

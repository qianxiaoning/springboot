package com.qxn.dubbo.service;

import java.util.List;

import com.qxn.pojo.Order;
import com.qxn.pojo.OrderShipping;

public interface DubboOrderService {

	String saveOrder(Order order);

	Order queryOrderByIdMybatisPlus(String orderId);

	Order queryOrderByIdSql(String orderId);

	OrderShipping queryOrderShipping(String orderId);

}

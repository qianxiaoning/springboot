package com.qxn.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qxn.dubbo.service.DubboOrderService;
import com.qxn.mapper.OrderItemsMapper;
import com.qxn.mapper.OrderMapper;
import com.qxn.mapper.OrderShippingMapper;
import com.qxn.pojo.Order;
import com.qxn.pojo.OrderItem;
import com.qxn.pojo.OrderShipping;

@Service
public class DubboOrderServiceImpl implements DubboOrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;
    /**
     *业务：
     *同时实现3张表入库的操作
     *事务控制 
     */
    @Override
    @Transactional
    public String saveOrder(Order order) {
        String orderId = ""+order.getUserId()+System.currentTimeMillis();
//		String uuid = UUID.randomUUID().toString();
//		String orderId = DigestUtils.md5DigestAsHex(uuid.getBytes());
        Date date = new Date();
        order.setOrderId(orderId)
                //订单状态设为1未付款
                .setStatus(1)
                .setCreated(date)
                .setUpdated(date);
        orderMapper.insert(order);
        System.out.println("订单入库");
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId)
                .setCreated(date)
                .setUpdated(date);
        orderShippingMapper.insert(orderShipping);
        System.out.println("订单物流入库");
        List<OrderItem> orderItems = order.getOrderItems();
        if(orderItems.size()>0) {
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrderId(orderId)
                        .setCreated(date)
                        .setUpdated(date);
                orderItemsMapper.insert(orderItem);
            }
        }
        System.out.println("订单商品入库");
        return orderId;
    }
    @Override
    public Order queryOrderByIdMybatisPlus(String orderId) {
		Order order = orderMapper.selectById(orderId);
        OrderShipping orderShipping = orderShippingMapper.selectById(orderId);
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id",orderId);
        List<OrderItem> orderItems = orderItemsMapper.selectList(queryWrapper);
        order.setOrderItems(orderItems);
        order.setOrderShipping(orderShipping);
        return order;
    }
    @Override
    public Order queryOrderByIdSql(String orderId) {
        return orderMapper.queryOrderByIdSql(orderId);
    }
    @Override
    public OrderShipping queryOrderShipping(String orderId) {
        OrderShipping orderShipping = orderShippingMapper.selectById(orderId);
        return orderShipping;
    }

}

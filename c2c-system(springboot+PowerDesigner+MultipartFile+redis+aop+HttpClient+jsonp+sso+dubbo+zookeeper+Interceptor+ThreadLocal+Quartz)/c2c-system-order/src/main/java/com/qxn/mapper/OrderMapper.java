package com.qxn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxn.pojo.Order;
import com.qxn.pojo.OrderShipping;

public interface OrderMapper extends BaseMapper<Order>{
	Order queryOrderByIdSql(String orderId);
}

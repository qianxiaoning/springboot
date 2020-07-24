package com.qxn.quartz;

import java.util.Calendar;
import java.util.Date;

import com.qxn.mapper.OrderMapper;
import com.qxn.pojo.Order;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

//准备订单定时任务
@Component
public class OrderQuartz extends QuartzJobBean{

	@Autowired
	private OrderMapper orderMapper;

	/**每1分钟，将超时订单状态改为6
	 * 条件：now() - created > 30分钟 && status = 1
	 * update c2csystem_order set status=6,updated=#{date} where status=1 and created < now() - 30分钟
	 */
	@Override
	@Transactional
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//设定30分钟超时
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -30);
		Date date = calendar.getTime();

		Order order = new Order();
		order.setStatus(6)
			.setUpdated(new Date());
		UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("status", "1").lt("created",date);

		orderMapper.update(order, updateWrapper);
		System.out.println("定时任务执行");
	}
}

package com.qxn.pj.common.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.qxn.pj.common.exception.ServiceException;
//@Component
public class TimeAccessInterceptor implements HandlerInterceptor {
	//在后端handler执行前执行，是否拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("====拦截器执行====");
		//获取日历对象
		int startHour = 7;
		int endHour = 24;
		Calendar c = Calendar.getInstance();
		//HOUR_OF_DAY 24小时
		c.set(Calendar.HOUR_OF_DAY, startHour);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		//允许访问的开始时间
		long start = c.getTimeInMillis();
		//允许访问的结束时间
		c.set(Calendar.HOUR_OF_DAY, endHour);
		long end = c.getTimeInMillis();
		//设置规则
		long cTime = System.currentTimeMillis();
		if(cTime < start || cTime > end) {
			throw new ServiceException(startHour + "点 ~ "+endHour + "点不可访问");
		}else {
			return true;
		}
		
	}
}

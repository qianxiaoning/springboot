package com.qxn.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.qxn.pj.common.vo.PageObject;
import com.qxn.pj.sys.entity.Logs;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Aspect
@Component
//切面优先级，数字越小越优先，默认随机顺序
@Order(1)
public class TimeAspectTest {
	/*一个对象menusServiceImpl上有多个切面时，就形成了一个切面链，
	 * 先执行的后结束
	 * */
	//粗粒度切入点表达式，精确到类
//	@Pointcut("bean(menusServiceImpl)")
	//针对所有的查询
//	@Pointcut("bean(*ServiceImpl)")
	//包名下
//	@Pointcut("within(*com.qxn.pj.sys.service.impl.*)")
	//包名及子包名下
//	@Pointcut("within(*com.qxn.pj.sys.service..*)")
	
	//细粒度切入点表达式，精确到方法
	//execution(返回值类型 包名.类名.方法名(参数列表))，里面实体类要全路径
//	@Pointcut("execution(* com.qxn.pj.sys.service..*.*(..))")
//	@Pointcut("execution(* com.qxn.pj.sys.service.impl.LogsServiceImpl.findPageObject(String, Integer))")
//	@annotation注解方法 - 见LogsServiceImpl.findPageObject
	@Pointcut("@annotation(com.qxn.pj.common.annotation.RequiredTime)")
	public void doTimePointCut() {}
	
	
	//以下两种写法都可以
//	@Before("bean(menusServiceImpl)")
	//用doTimePointCut这个名字，好处就是把切入对象名字提取出来，方便维护
	@Before("doTimePointCut()")
	public void beforeAdvice() {
		log.info("time:beforeAdvice");
	}
	@After("doTimePointCut()")
	public void afterAdvice() {
		log.info("time:afterAdvice");
	}
	@AfterReturning("doTimePointCut()")
	public void afterReturningAdvice() {
		log.info("time:afterReturningAdvice");
	}
	@AfterThrowing("doTimePointCut()")
	public void afterThrowingAdvice() {
		log.info("time:afterThrowingAdvice");
	}
	@Around("doTimePointCut()")
	public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable{
		log.info("time:aroundAdvice.before");
		Object result = jp.proceed();
		log.info("time:aroundAdvice.after");
		return result;
	}
}

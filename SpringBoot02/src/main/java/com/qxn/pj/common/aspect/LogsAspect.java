package com.qxn.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qxn.pj.common.annotation.RequiredLog;
import com.qxn.pj.common.util.IPUtils;
import com.qxn.pj.sys.entity.Logs;
import com.qxn.pj.sys.entity.Users;
import com.qxn.pj.sys.service.LogsService;

import lombok.extern.slf4j.Slf4j;
/*
 * 1.切入点定义
 * 2.通知的定义（扩展功能）
 * */
@Slf4j
//声明是切面
@Aspect
//交给spring管理
@Component
public class LogsAspect {
	//菜单模块所有方法执行时，加日志
	//menusServiceImpl此bean中所有方法的集合 即 切入点
	/*bean(bean名称或一个表达式)，如：
	 * bean(menusServiceImpl)
	 * bean(*ServiceImpl)
	 * */
	@Pointcut("@annotation(com.qxn.pj.common.annotation.RequiredLog)")
	public void logPointCut() {}
	/*五种类型：
	 * @Before方法执行前
	 * @AfterReturning方法return后
	 * @AfterThrowing方法异常后
	 * @After最终通知（finally）
	 * @Around环绕通知，最常用，优先级最高
	 * try{
	 * 	@Before
	 * 	核心业务
	 * 	@AfterReturning
	 * }catch(Exception e){
	 * 	...
	 * 	@AfterThrowing
	 * }finally{
	 * 	...
	 * 	@After
	 * }
	 * */
	//@Around环绕通知，目标方法执行之前之后，添加扩展功能，或调用下一个切面对或目标方法
	/*执行bean(menusServiceImpl)切入点的方法的时候，
	 * 触发aroundAdvice
	 * */
	@Around("logPointCut()")
	/*重点：menusServiceImpl任意方法执行时，
	 * 都会调用此aroundAdvice方法
	 * */
	//ProceedingJoinPoint只应用于@Around
	public Object aroundAdvice(ProceedingJoinPoint jp)throws Throwable{
		long start = System.currentTimeMillis();
		log.info("start:"+start);
		//jp封装了目标方法信息，还有切面信息
		Object result = jp.proceed();//调用下一次切面或目标方法
		long end = System.currentTimeMillis();
		log.info("end:"+end);
		//jp封装的是目标方法信息
		//保存日志
		insertLog(jp,(end-start));
		return result;
	}
	@Autowired
	private LogsService logsService;
	private void insertLog(ProceedingJoinPoint jp, long time) throws Throwable {
		/*1.获取用户行为日志（id,username,operation,method,
		 * params,operationTime,ip,createdTime）
		 * */
		Class<?> targetCls = jp.getTarget().getClass();
		//类全名
		String targetClsName = targetCls.getName();
		//方法签名，包含方法名字，方法参数类型，方法返回值类型
		//获取方法名和方法上的注释值
		MethodSignature ms = (MethodSignature)jp.getSignature();
		//方法名s.getName();
		String targetObjectMethodName = 
				targetClsName + "." + ms.getName();
		Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
		String operation = requiredLog.value();
		//参数数组转字符串
		String targetMethodParams = 
				Arrays.toString(jp.getArgs());
		Class clz;
		
		if(jp.getArgs().length!=0) {
			clz = jp.getArgs()[0].getClass();
			
			if(clz==Users.class) {
				targetMethodParams = ((Users)jp.getArgs()[0]).toStringValues();
			}
		}
		//2.封装用户行为日志（Logs）
		Logs entity = new Logs();
		entity.setIp(IPUtils.getIpAddr());
		entity.setUsername("admin");		
		entity.setOperation(operation);
		entity.setMethod(targetObjectMethodName);
		entity.setParams(targetMethodParams);
		entity.setOperationTime(time);
		entity.setCreatedTime(new Date());
		//3.调用service方法insertLog将日志写入到数据库
		logsService.insertLog(entity);
	}
	
}

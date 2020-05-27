package com.qxn.pj.common.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SpringAsyncConfig implements AsyncConfigurer{
	private int corePoolSize = 2;
	private int maxPoolSize = 5;
	private int keepAliveSeconds = 10*60;
	private String threadNamePrefix = "qxn-service-thread-";
	private int queueCapacity = 5;	
	/*SimpleAsyncTaskExecutor对象，
	 * 1.每次请求都会创建新线程
	 * 2.可以对并发请求限流（原理：阻塞）
	 * */
//	@Override
//	public Executor getAsyncExecutor() {
//		//
//		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
//		System.out.println("====getAsyncExecutor()====");
//		//限流100个线程，大于100阻塞
//		taskExecutor.setConcurrencyLimit(100);
//		return taskExecutor;
//	}
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = 
				new ThreadPoolTaskExecutor();
		//设置核心线程数，默认8
		taskExecutor.setCorePoolSize(corePoolSize);
		//设置最大线程数，默认21亿
		taskExecutor.setMaxPoolSize(maxPoolSize);
		//设置空闲时间
		taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		//设置前缀（推荐）
		taskExecutor.setThreadNamePrefix(threadNamePrefix);
		//设置队列容量，默认阻塞式队列(LinkedBlockingQueue)
		taskExecutor.setQueueCapacity(queueCapacity);
		//含有
		/*含义：
		 * 核心线程数2都在工作中，再来请求放入队列，2个线程队列都满了，再创建新线程，
		 * 最大线程数5，再有请求，阻塞
		 * */
		//设置拒绝处理的策略（当无法处理新的任务时，执行的策略）
		//使用调用者线程进行处理，tomcat线程
		taskExecutor.setRejectedExecutionHandler(
				new ThreadPoolExecutor.CallerRunsPolicy());
		//拒绝策略，日志输出
		taskExecutor.setRejectedExecutionHandler(
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						log.warn("没有足够的线程处理当前任务");
						//日志输出：020-05-26 10:51:13.389  WARN 5752 --- [p-nio-84-exec-1] c.q.pj.common.config.SpringAsyncConfig   : 没有足够的线程处理当前任务
					}
				});
		//进行池的初始化
		taskExecutor.initialize();
		return taskExecutor;
	}
	//异步任务执行过程中的错误处理
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				log.error("执行异步任务时出现了未知的错误",ex);
			}
		};
	}
}


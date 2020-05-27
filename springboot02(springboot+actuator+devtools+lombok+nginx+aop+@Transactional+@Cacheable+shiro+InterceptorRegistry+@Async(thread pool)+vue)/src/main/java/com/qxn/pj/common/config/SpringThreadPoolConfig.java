package com.qxn.pj.common.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

@Setter
@Configuration
@ConfigurationProperties("async-thread-pool")
public class SpringThreadPoolConfig {
//	@Value("${async-thread-pool.corePoolSize}")
	private int corePoolSize;
//	@Value("${async-thread-pool.maxPoolSize}")
	private int maxPoolSize;
//	@Value("${async-thread-pool.keepAliveSeconds}")
	private int keepAliveSeconds;
//	@Value("${async-thread-pool.queueCapacity}")
	private int queueCapacity;
	//创建线程工厂，为创建线程时，给线程起名
	private ThreadFactory threadFactroy = new ThreadFactory() {
		//cas算法，有原子性
		private AtomicLong count = new AtomicLong(1);
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r,"qxn-server-thread-name-" + 
		//自增
		count.getAndIncrement());
		}
	};
	//写法2
	@Bean
	public Executor asyncExecutor() {
		System.out.println("corePoolSize = " + corePoolSize);
		//构建阻塞式队列
		BlockingQueue<Runnable> workQueue = 
				new LinkedBlockingDeque<>(queueCapacity);
		//构建线程池对象（tomcat中默认用的池）
		ThreadPoolExecutor executor = 
		//进ThreadPoolExecutor中，ctrl+o看ThreadPoolExecutor的参数
		new ThreadPoolExecutor(corePoolSize, 
				maxPoolSize, 
				keepAliveSeconds, 
				TimeUnit.SECONDS, //空闲时间的单位
				workQueue,
				threadFactroy,
				//拒绝执行的策略，使用调用者线程
				new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
}

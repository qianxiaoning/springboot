package com.qxn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.qxn.annotation.CacheFind;
import com.qxn.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

@Aspect		//标识切面
@Component	//将该类交给spring容器管理
public class CacheAspect {

    //当前切面位于common中.必须添加required = false
    @Autowired(required = false)
//    private Jedis jedis;
//    private ShardedJedis jedis; //分片机制
//    private Jedis jedis;	//哨兵机制
    private JedisCluster jedis;	//集群
    /**
     * 通知选择:环绕通知
     * 步骤:
     * 	1.动态生成key  包名.类名.方法名::parentId
     *  2.通过key查询redis缓存
     */
    @Around("@annotation(cacheFind)") //直接获取注解的对象
    public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind) {
        String key = getKey(joinPoint,cacheFind);
        String result = this.jedis.get(key);
        Object data = null;
        try {
            if(StringUtils.isEmpty(result)) {
                //缓存无数据，目标方法执行查询数据
                data = joinPoint.proceed();
                String value = ObjectMapperUtil.toJSON(data);
                //看用户是否设置超时时间
                if(cacheFind.seconds()==0) {
                    this.jedis.set(key, value);
                }else {
                    //设置超时时间
                    this.jedis.setex(key,cacheFind.seconds(), value);
                }
                System.out.println("AOP数据库查询");
            }else {
                //缓存有数据
                Class returnClass = getClass(joinPoint);
                data = ObjectMapperUtil.toObject(result,returnClass);
                System.out.println("AOP查询缓存");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return data;
    }


    //获取切面方法的返回值类型
    private Class getClass(ProceedingJoinPoint joinPoint) {
        //反射包下的MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getReturnType();
    }

    //动态生成key，包名.类名.方法名::parentId
    private String getKey(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
        //1.判断用户是否定义key值
        String key = cacheFind.key();
        if(!StringUtils.isEmpty(key)) {
            return key; //返回用户自己定义的key
        }
        //包名.类名.方法名::parentId
        //表示需要自动生成key
        String className =
                joinPoint.getSignature().getDeclaringTypeName();
        String methodName =
                joinPoint.getSignature().getName();
        //joinPoint.getArgs()得到参数列
        Object[] args = joinPoint.getArgs();
        key = className+"."+methodName+"::"+args[0];
        return key;
    }
}

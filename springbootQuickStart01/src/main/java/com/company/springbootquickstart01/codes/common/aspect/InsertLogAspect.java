package com.company.springbootquickstart01.codes.common.aspect;

import com.company.springbootquickstart01.codes.common.util.ObjectMapperUtil;
import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//切面
@Aspect
@Component
public class InsertLogAspect {
    //切点
    @Pointcut("@annotation(com.company.springbootquickstart01.codes.common.annotation.InsertLog)")
    public void logPointCut() {}
    //通知
    @Around("logPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint jp)throws Throwable{
        long beforeTime = System.currentTimeMillis();
        //调用下一次切面或目标方法
        JsonResult result = (JsonResult)jp.proceed();
        long time = System.currentTimeMillis() - beforeTime;
        if(result.getCode() == 200){
            //插入日志
            insertLog(jp,time,result);
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint jp, long time, JsonResult result) {

    }
}

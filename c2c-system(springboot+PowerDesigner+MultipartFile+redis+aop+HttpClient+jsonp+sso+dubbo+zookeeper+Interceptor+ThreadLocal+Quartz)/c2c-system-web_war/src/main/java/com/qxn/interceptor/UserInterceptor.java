package com.qxn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qxn.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.qxn.pojo.User;
//import com.qxn.util.CookieUtil;
import com.qxn.util.ObjectMapperUtil;
import com.qxn.util.ThreadLocalUtil;

import redis.clients.jedis.JedisCluster;

@Component
public class UserInterceptor implements HandlerInterceptor{
    @Autowired
    private JedisCluster jedisCluster;
    private static final String C2CSYSTEM_USER = "C2CSYSTEM_USER";
    private static final String C2CSYSTEM_USERNAME = "C2CSYSTEM_USERNAME";
    private static final String C2CSYSTEM_TICKET = "C2CSYSTEM_TICKET";
    //方法执行之前 是否放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 不登录不能访问购物车
         * 实现思路：
         * 1.获取cookie
         * 2.获取redis
         */
        System.out.println("preHandle");
        String cookieValue = CookieUtil.getCookieValue(request, C2CSYSTEM_USERNAME);
        if(!StringUtils.isEmpty(cookieValue)) {
            String redisValue = jedisCluster.hget(cookieValue,"C2CSYSTEM_USERJSON");
            if (!StringUtils.isEmpty(redisValue)) {
                User user = ObjectMapperUtil.toObject(redisValue, User.class);
//                request.setAttribute(C2CSYSTEM_USER,user);
                ThreadLocalUtil.set(user);
                return true;
            }
        }
        return false;//拦截，返回空，前端控制路由
    }
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        /**
//         * 不登录不能访问购物车
//         * 实现思路：
//         * 1.获取cookie
//         * 2.获取redis
//         */
//        System.out.println("preHandle");
//        String cookieValue = CookieUtil.getCookieValue(request, C2CSYSTEM_TICKET);
//        if(!StringUtils.isEmpty(cookieValue)) {
//            String redisValue = jedisCluster.get(cookieValue);
//            if (!StringUtils.isEmpty(redisValue)) {
//                User user = ObjectMapperUtil.toObject(redisValue, User.class);
////                request.setAttribute(C2CSYSTEM_USER,user);
//                ThreadLocalUtil.set(user);
//                return true;
//            }
//        }
//        return false;//拦截，返回空，前端控制路由
//    }
    //方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }
    //方法完成最后阶段
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion");
        //数据销毁
//        request.removeAttribute(C2CSYSTEM_USER);
        ThreadLocalUtil.remove();
    }
}

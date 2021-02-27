package com.company.springbootquickstart01.codes.common.interceptor;

import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

//允许访问时间
@Component
public class TimeAllowedInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    //方法执行之前 是否放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在拦截器中获取其他bean
//        System.out.println(redisUtil);
//        System.out.println(request.getRequestURL());
//        System.out.println("当前时间 " + new Date());
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
            throw new ServiceException(startHour + "点 ~ "+endHour + "点之外，不能访问");
        }else {
            return true;
        }
    }

    @Override
    //方法执行之后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    //视图渲染完成后，清理资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

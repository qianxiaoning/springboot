package com.company.springbootquickstart01.codes.common.interceptor;

import com.company.springbootquickstart01.codes.common.globalException.ServiceException;
import com.company.springbootquickstart01.codes.common.util.ConstantsUtil;
import com.company.springbootquickstart01.codes.common.util.CookieUtil;
import com.company.springbootquickstart01.codes.common.util.IPUtil;
import com.company.springbootquickstart01.codes.common.util.ThreadLocalUtil;
import com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay.LoginVo;
import com.company.springbootquickstart01.libs.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

//登录验证拦截器
@Component
public class LoginedValidateInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    //方法执行之前 是否放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        //获取request的cookie中的user_uuid，加上自身ip地址，去redis查找用户信息，
        //能找到就是已登录，将redis值存入ThreadLocal中，给service层使用，在拦截器afterCompletion中清除
        //找不到就是未登录，抛出服务器异常
        String uuid = CookieUtil.getCookieValue(request, ConstantsUtil.UUID_COOKIE_NAME);
        String ip = "ip"+ IPUtil.getIpAddr(request).replace(":","：");
        String token = uuid + ip;
        LoginVo loginVo = (LoginVo) redisUtil.get("UserToken:" + token);
        if(loginVo == null){
            throw new ServiceException("token已过期，请重新登录");
        }else{
            ThreadLocalUtil.put("userInfo",loginVo);
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
        ThreadLocalUtil.removeOne("userInfo");
    }
}

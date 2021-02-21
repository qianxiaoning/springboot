package com.company.springbootquickstart01.codes.common.filter;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//过滤器顺序靠类名称排序
//urlPatterns是拦截规则，⽀持正则，在此添加需要过滤的路径
//filterName过滤器name属性，urlPatterns过滤器url匹配模式，initParams过滤器初始化参数
@WebFilter(filterName = "a_AnnotationFilter",urlPatterns = {"/api/*"},
        initParams = { @WebInitParam(name = "character", value = "UTF-8"),
                @WebInitParam(name = "name", value = "qxn"),
                @WebInitParam(name = "excludedUrls", value = "/api/filter/filterTest,/api/xxx")}
            )
//作用场景：
//对请求进行预处理，对响应进行后处理
public class A_AnnotationFilter implements Filter {
    private String character;
    private List<String> excludedUrlList;
    //初始化设置操作，容器加载时
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("A_AnnotationFilter init======");
        //filterConfig.getFilterName()过滤器名称
        //filterConfig.getInitParameter(String s)初始化参数的值
        //filterConfig.getInitParameterNames()初始化参数名称
//        System.out.println(filterConfig);
        character = filterConfig.getInitParameter("character");
//        System.out.println(filterConfig.getInitParameter("excludedUrls"));
        //获取排除urls
        String excludedUrls = filterConfig.getInitParameter("excludedUrls");
        excludedUrlList = Arrays.asList(excludedUrls.split(","));
    }
    //过滤行为
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置utf8格式
//        servletRequest.setCharacterEncoding(character);
//        servletResponse.setCharacterEncoding(character);
//        servletResponse.setContentType("text/html;charset=UTF-8");
        //过滤请求参数中非法字符，将servletRequest中非法字符去除，再放入filterChain.doFilter中
        //...
        //管理特定格式请求
//        HttpServletRequest req = (HttpServletRequest)servletRequest;
//        String url = req.getRequestURI();
//        int index = url.lastIndexOf(".");
//        System.out.println("url:" + url);
//        String type = index > -1 ? url.substring(index) : null;
//        if(".css".equals(type) || ".js".equals(type) || ".html".equals(type)
//                || "png".equals(type) ){
//            System.out.println("这是静态资源:" + url);
//        }
        //执行下一个过滤器或到达目标
        filterChain.doFilter(servletRequest, servletResponse);
    }
    //清除过滤器操作，容器销毁时
    @Override
    public void destroy() {
//        System.out.println("A_AnnotationFilter destroy======");
    }
}

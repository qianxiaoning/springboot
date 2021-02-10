package com.company.springbootquickstart01.codes.common.filter;

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
        initParams = { @WebInitParam(name = "name", value = "qxn"),
                @WebInitParam(name = "age", value = "18"),
                @WebInitParam(name = "excludedUrls", value = "/api/user/findUserById,/api/xxx")}
            )
//作用场景：
// 1、doFilter中根据条件决定是否调用chain.doFilter(request, response)方法， 是否让目标资源执行
// 2、在让目标资源执行之前，可以对request\response作预处理，再让目标资源执行
// 3、在目标资源执行之后，可以捕获目标资源的执行结果，从而实现一些特殊的功能
// 访问接口时，判断用户是否登录//过滤请求参数
public class A_AnnotationFilter implements Filter {
    private List<String> excludedUrlList;
    //初始化设置操作，容器加载时
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("A_AnnotationFilter init======");
        //filterConfig.getFilterName()过滤器名称
        //filterConfig.getInitParameter(String s)初始化参数的值
        //filterConfig.getInitParameterNames()初始化参数名称
        System.out.println(filterConfig);
        System.out.println(filterConfig.getInitParameter("excludedUrls"));
        //获取排除urls
        String excludedUrls = filterConfig.getInitParameter("excludedUrls");
        excludedUrlList = Arrays.asList(excludedUrls.split(","));
    }
    //过滤行为
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对request和response进行一些预处理
//        servletRequest.setCharacterEncoding("UTF-8");
//        servletResponse.setCharacterEncoding("UTF-8");
//        servletResponse.setContentType("text/html;charset=UTF-8");
//        filterChain.doFilter(servletRequest, servletResponse); // 让目标资源执行，放行
        //过滤排除的urls
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        if(excludedUrlList.contains(requestURI)){
            System.out.println("这些路径不通过");
        }else{
            System.out.println("这些路径可以通过");
            filterChain.doFilter(servletRequest,servletResponse);
//            String sToken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
//            if (sToken != null) {
//                Map<String, Object> map = TokenUtils.parseToken(sToken);
//                if (map == null) {
//                    ((HttpServletResponse)response).sendRedirect("/web/login");
//                }
//            } else {
//                ((HttpServletResponse)response).sendRedirect("/web/login");
//            }
        }
    }
    //清除过滤器操作，容器销毁时
    @Override
    public void destroy() {
        System.out.println("A_AnnotationFilter destroy======");
    }
}

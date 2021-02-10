package com.company.springbootquickstart01.codes.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//过滤器顺序靠类名称排序
@WebFilter(filterName = "b_AnnotationFilter",urlPatterns = {"/api/*"})
public class B_AnnotationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("B_AnnotationFilter init======");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //清除过滤器操作，容器销毁时
    @Override
    public void destroy() {
        System.out.println("B_AnnotationFilter destroy======");
    }
}

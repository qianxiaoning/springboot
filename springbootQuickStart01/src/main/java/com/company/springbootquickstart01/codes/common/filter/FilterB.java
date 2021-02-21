package com.company.springbootquickstart01.codes.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class FilterB implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("FilterB init======");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("FilterB doFilter======");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //清除过滤器操作，容器销毁时
    @Override
    public void destroy() {
//        System.out.println("FilterB destroy======");
    }
}

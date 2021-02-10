package com.company.springbootquickstart01.codes.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class FilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FilterA init======");
        System.out.println(filterConfig.getInitParameter("name"));
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterA doFilter======");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //清除过滤器操作，容器销毁时
    @Override
    public void destroy() {
        System.out.println("FilterA destroy======");
    }
}

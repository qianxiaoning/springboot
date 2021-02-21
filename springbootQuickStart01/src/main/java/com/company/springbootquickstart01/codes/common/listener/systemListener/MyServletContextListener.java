package com.company.springbootquickstart01.codes.common.listener.systemListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class MyServletContextListener implements ServletContextListener {
    //初始化数据，缓存下来，减轻数据库压力
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener init");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userInfo",new HashMap<String,Object>(){{
            put("name", "qxn");
            put("age", 18);
        }});
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener destory");
    }
}

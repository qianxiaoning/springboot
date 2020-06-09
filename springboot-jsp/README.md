### springboot-jsp
#### springboot-mybatisPlus-jsp
```
概要：
1.sync.jsp jstl同步请求
2.async.jsp fetch异步请求
```
---
```
流程：
1.新建springboot war包项目
new-project-spring initializr-packaging:war-project location:...\projectName
2.pom依赖
springboot官网建议html。如果要整合jsp，需要加依赖：
jstl(jstl表达式)
javax.servlet-api(servlet依赖，使用HttpServletRequest和HttpServletResponse)//暂时可以不加？
tomcat-embed-jasper(告诉tomcat加载jsp)
3.配置文件加上 spring.mvc.view.prefix/suffix
4.生成src\main\webapp\WEB-INF\pages\文件夹
ctrl+alt+shift+s打开project structure
在project settings-modules-springbootxxx-Web下，复制Web Resource Directory路径，
路径后面添加"\WEB-INF\pages\web.xml"，删除web.xml，Apply，OK
5.添加Controller，return jsp名;
6.jsp引入jstl
```

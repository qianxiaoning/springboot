### springboot01
#### springboot入门+html
```
概要：
1.传统spring
配置复杂（大量xml）
依赖冲突
扩展复杂（分布式部署，微服务架构）
2.springboot开箱即用，简化配置
自动配置
起步依赖
健康检查（项目监控机制）
3.SpringBoot01Application.java启动类
4.BeanA.java自动注册bean对象
5.Companys.java pojo使用lombok
6.src/main/resources/templates/pages下面放html页面
7.application.properties配置文件
8.SpringBoot01ApplicationTests.java
测试ApplicationContext ctx
```
---
```
流程：
sts
1.工作区编码utf-8
2.maven安装，设置
3.spring starter project
name,packaging:jar,group,artifact,package
finish
4.整合数据源，HikariCP，右键pom.xml-spring-edit starters
mysql driver,jdbc api（事务的处理）
扩展整合druid
5.springboot整合mybatis
pom.xml mybatis framework
application.properties中配置mapper扫描
6.添加内置tomcat
添加依赖thymeleaf,spring web starter
boot dashboard启动项目
7.spring web视图解析器
application.properties中
8.在src/main/resources下创建html目录，服务端能访问的位置
访问时url不需要加项目名
得手动重启
9.服务器简易配置
端口，最大线程数，context路径
10.项目应用扩展
健康检查spring boot actuator
http://localhost/actuator/health
查看当前状态
开发环境配置management.endpoints.web.exposure.include=*
http://localhost/actuator/beans
查看spring中所有bean信息
11.热部署配置的实现
添加spring boot devtools依赖
配置文件配置
12.lombok插件
添加依赖lombok
复制全路径找到lombok-xxx.jar所在目录
执行java -jar lombok-xxx.jar
找到sts目录，选中sts.exe文件，install
安装后，sts.exe目录会有一个lombok.jar文件
sts.ini最后一行是lombok的安装目录
pojo对象即可使用lombok注解
```

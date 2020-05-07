### SpringBoot_html_fetchPage
#### springboot内嵌html开发(页面、js，fetch动态加载)
```
概要：
1.利用fetch动态加载html,css，动态添加script标签
2.封装fetch方法，myFetch(url,data,handle,successFun)，实现接口返回code统一处理
```
---
```
流程：
1.添加依赖
2.application.yml配置文件
3.创建PageController.java
4.创建前端文件，static/*.html，css/，js/
5.配置html的thymeleaf路径，和静态资源的static路径
6.编写前端页面代码
7.后台entity=>dao=>xml=>vo(JsonResult)=>service=>serviceImpl
=>ServiceException异常类=>JsonResult=>
controller=>GlobalExceptionHandler=>frontEnd
```

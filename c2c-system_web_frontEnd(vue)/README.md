### c2c-system_web_frontEnd(vue)
#### c2c-system前台站点
#### 配套后台c2c-system(springboot+mybatisPlus+PowerDesigner+MultipartFile+nginx+linux+redis+aop+HttpClient+jsonp+sso+dubbo+zookeeper+Cookie+Interceptor+ThreadLocal+Quartz)
```
概要：
1.头部组件Head
2.v-html渲染的dom，样式不能有scoped属性
3.jsonp跨域获取数据，
定义取数据方法，testJsonp.getJsonpData，testJsonp.getJsonpObjectData
挂载到window上，
调用utils.putJsonpScript执行
4.http/http.js
//匹配后台未登录权限拦截器
//未登录，res返回值为空
else if(!res){}
```

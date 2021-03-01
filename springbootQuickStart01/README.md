### springbootQuickStart01
#### springboot脚手架01
- 结构：（未更新）
```
codes 逻辑代码层：
 .common
  .entity 公共实体类
   .BasePojo pojo基类
  .globalException
   .GlobalExceptionHandler 全局异常类
   .ServiceException 异常对象
  .util
   .CookieUtil cookie工具类
   .IPUtil ip工具类
   .JasypUtil cookie工具类
   .IPUtil Jasyp加解密工具类
   .ObjectMapperUtil json和对象互转工具类
   .ServiceUtil 业务层工具类
   .ThreadLocalUtil threadlocal工具类
  .vo
   .JsonResult 接口返回vo对象
   .PageObject 分页vo对象

libs 第三方库层：
 .loginRegisterProcess 注册登录过程
  .normalWay 普通方法
 .mybatisPlus
  .mybatisPlusConfig mybatisPlus配置类 
 .redis
  .RedisConfig redis配置类
  .RedisUtil redis工具类
 .swagger
  .SwaggerConfig swagger配置类

springBootTest bean测试类：
 .BeanTest
test bean简单测试类：
 .SimpleTest
```
---
- 过程：
```
大纲：
redis,swagger,mybatisPlus,过滤器，拦截器,监听器,普通登录注册流程,
登录验证拦截器,springboot事务,日志记录注解aop,MultipartFile对象文件上传

1 结合redis
1.1 centos安装redis
重点部分：
修改redis.conf
#bind 127.0.0.1
protected-mode no
requirepass 密码
daemonize yes
1.2
pom.xml spring-boot-starter-data-redis依赖
1.3
application.yml增加redis配置
1.4
引入spring-boot-starter-data-redis后，
会自动注入redisTemplate(String,Object)和stringRedisTemplate(String,String)对象
redisTemplate(String,Object)的keyJsonResult,value默认序列化方式是JdkSerializationRedisSerializer，会显示乱码
libs.redis.RedisConfig重写redisTemplate对象，
将RedisTemplate的key,value的序列化方式指定为StringRedisSerializer和GenericJackson2JsonRedisSerializer
使用方式：
redisTemplate:
redisTemplate.opsForValue().set("obj",obj);
Object obj = redisTemplate.opsForValue().get("obj");

stringRedisTemplate:
stringRedisTemplate.opsForValue().set("stringRedis","stringRedis");
String stringRedis = stringRedisTemplate.opsForValue().get("stringRedis");

引入RedisUtil，libs.redis.RedisUtil工具类
RedisUtil:
redisUtil.set("userRedisUtil",user);
User userRedisUtil = (User) redisUtil.get("userRedisUtil");
redisUtil.hset("a", "a", "b");
redisUtil.hget("a", "a");
redisUtil.expire("userRedisUtil",1000);
redisUtil.hasKey("userRedisUtil");
redisUtil.del("userRedisUtil");

2 结合swagger
2.1 pom.xml springfox-swagger2 springfox-swagger-ui
2.2 swagger配置
libs.swagger.SwaggerConfig
2.3 yml配置
2.4 逻辑代码层添加注解
controller类上 @Api(tags = "用户操作接口")
controller方法上 @ApiOperation("id查找单个用户")
controller方法上 @ApiImplicitParam(name="id",value="用户id",required = true)
entity上 @ApiModel(value="user对象", description="")
entity属性上 @ApiModelProperty(value = "用户id",required = true)
2.5 访问
http://localhost:8000/swagger-ui.html

3 结合mybatisPlus
3.1 pom mybatis-plus-boot-starter
3.2 yml
3.3 逻辑代码层
service层 extends IService<Do>
mapper层 extends BaseMapper<Do>
entity上： 
@TableId(value = "id", type = IdType.ASSIGN_ID)//雪花算法自动生成id
@TableLogic//表字段逻辑处理
3.4 乐观锁，使用mybatisplus进行改删操作时触发，+1
3.4.1 在MybatisPlusConfig的MybatisPlusInterceptor核心插件中，
添加OptimisticLockerInnerInterceptor乐观锁插件
3.4.2
在实体类字段上添加@Version注解 
3.5 AutoGenerator代码生成器
3.5.1 添加pom依赖mybatis-plus-generator、velocity-engine-core
3.5.2 MybatisPlusCodeGenerator 代码生成器类
3.5.3 输入tableNames
3.5.4 执行main方法

4 过滤器
法1 @WebFilter法
启动类添加@ServletComponentScan注解
类实现Filter接口，在类上加@WebFilter注解
通过@WebFilter注解配置过滤器信息，名称filterName、url匹配模式urlPatterns、过滤器初始化参数initParams
doFilter方法执行顺序看类名称排序
过滤器中可直接引入spring的bean对象
法2 FilterRegistrationBean对象注册法
类实现Filter接口，加上@Component，交给spring管理。
注册FilterRegistrationBean对象，自动注入过滤器对象，将引入的过滤器注入FilterRegistrationBean对象中，
由FilterRegistrationBean对象配置过滤器信息，setName(String)、setUrlPatterns(Collection)、
setInitParameters(Map)
doFilter方法执行顺序由setOrder(int)方法中数字决定，越小越优先
过滤器中可引入spring的bean对象
4.1 过滤器init方法中能获取到过滤器信息filterConfig，将初始化参数存入ThreadLocal，供doFilter方法使用
4.2 doFilter方法，能对request/response作预处理，或者请求拦截

5 拦截器
5.1 类上加@Component交给spring管理，类实现HandlerInterceptor接口，重写preHandle、postHandle、afterCompletion方法
5.2 类实现WebMvcConfigurer接口，重写addInterceptors方法，自动注入拦截器对象
@Autowired
private AInterceptor aInterceptor;
注入拦截器
registry.addInterceptor(aInterceptor)
//添加拦截路径
.addPathPatterns("/**")
//排除拦截路径
.excludePathPatterns("/login");
5.3 拦截器交给spring管理后，拦截器中可以引入spring的bean对象

6 监听器
系统监听器：
监听servletContext、HttpSession、servletRequest等域对象的创建和销毁事件
法1：
将MyServletContextListener监听器
通过ServletListenerRegistrationBean.setListener(new MyServletContextListener());注册
法2：
监听器上添加@WebListener注解，启动类添加@ServletComponentScan注解
自定义监听事件：
6.1 AEvent定义事件
6.2 AEventListener定义监听器，获取事件中的信息，进行逻辑处理，或者通知别的微服务
6.3 ATrigger触发事件
6.4 BeanTest.test2测试

7 过滤器，拦截器，监听器使用场景
总结：
设计模式：
过滤器Filter：对请求进行预处理，对响应进行后处理
拦截器Interceptor：获取请求的上下文，拦截不符合要求的请求，实现需要的业务逻辑
监听器Listener：当一个事件发生的时候，获得这个事件发生的详细信息，实现业务逻辑，但不干预这个事件本身的进程

7.1 过滤器：
过滤器基于函数回调
过滤器依赖servlet，只能在servlet容器中，在Servlet前后起作用，
对用户请求进行预处理，对HttpServletResponse进行后处理。
不能定义业务逻辑执行前、后等，仅仅是请求到达就执行
太细的话，还是建议用interceptor

过滤器可以对几乎所有的请求起作用，拦截器只能对action请求起作用
Filter可以通过通配符对web服务器管理的所有web资源：例如Jsp，Servlet，静态图片文件，静态html文件等进行拦截

作用场景：
过滤器设置字符编码（CharacterEncodingFilter），解决post乱码，过滤敏感低俗危险词汇，过滤掉没用的参数，
压缩响应信息。

7.2 拦截器：
拦截器基于反射，拦截器属于spring组件，不依赖servlet
拦截器更细，在controller，service，mapper层都可以使用拦截器，对该层进行拦截
拦截器能够深入到方法前后、异常抛出前后等

拦截器，可以访问action上下文、值栈里的对象，而过滤器不能
可以判断用户是否登录，用户访问权限验证，判断当前是否是购票时间，登录日志功能，安全功能。

拦截器和过滤器的一个区别：
拦截器不一定会到达目标，可以拒绝请求，过滤器一定会到达目标，但是在到达目标前后会进行一些操作

在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次？

拦截器只能针对URL做拦截，而AOP能针对具体的代码，更灵活
拦截器一般位于Controller层，aop一般作用与Service层

aop：
优势在于可以获取执行方法的参数
日志记录，性能统计，安全控制，事务处理，异常处理

拦截器只能用在controller层，aop能用在service层

7.3 监听器：
统计统计网站访问量、在线人数和在线用户，利用HttpSessionLisener
系统启动时加载初始化信息，利用ServletContextListener
记录用户访问路径。

监听器用于监听web应用中某些对象、信息的创建、销毁、增加，修改，删除等动作的发生，然后作出相应的响应处理。
当范围对象的状态发生变化的时候，服务器自动调用监听器对象中的方法。

8 普通注册
8.1 密码加密保存 
param.setPassword(JasypUtil.encryptWithSHA512(param.getPassword()));
8.2 调用mybatisplus的雪花算法
Long id = IdWorker.getId(user);
8.3 ServiceUtil.createEntity反射给实例设置属性值
8.4 备选：mybatisPlus自动填充功能
8.4.1 
User：注解填充字段
@TableField(fill = FieldFill.INSERT)
private Date createTime;
@TableField(fill = FieldFill.INSERT_UPDATE)
private Date updateTime;
8.4.2 MybatisPlusConfig实现类MyMetaObjectHandler
重写insertFill、updateFill方法。这样使用mybatisplus insert/update方法时就会自动填充值

9 普通登录
9.1 数据库密码解密与参数密码比较
String password = JasypUtil.decryptWithSHA512(user.getPassword());
password.equals(param.getPassword())
9.2 成功登录则，
uuid和用户ip生成token，
String uuid = UUID.randomUUID().toString();
String ip = IPUtil.getIpAddr(request);
String token = uuid + ip;
token为key，用户信息存入redis，
redisUtil.set("UserToken:"+token,loginVo,7*24*3600);
uuid存入浏览器一级域名的cookie中，实现cookie在二级域名共享，作用于分布式服务单点登录（一处登录，处处通行）
CookieUtil.addCookie(request, response, "user_uuid",uuid,7*24*3600, ConstantsUtil.COOKIE_DOMAIN);

10 登录验证拦截器验证是否为登录状态
10.1 将LoginedValidateInterceptor登录验证拦截器，放入InterceptorConfig拦截器配置类，
排除登录注册等接口路径。
10.2 从cookie中取出uuid，加上ip，组成token，去redis中查询值，
查不到说明未登录或者登录已过期，抛出异常。
能查到说明是登录状态，将用户信息放入threadLocal中，放行请求。

11 springboot事务
在serviceImpl的两条及以上改数据库操作的方法上，使用事务，
添加@Transactional(rollbackFor = {Exception.class})注解

12 日志记录注解aop
12.1 添加InsertLog日志注解
12.2 添加InsertLogAspect日志切面，定义切点（InsertLog注解方式），通知类型（aroundAdvice环绕通知），
通过ProceedingJoinPoint连接点，反射获取日志需要的参数
12.3 在需要的方法上添加@InsertLog("xxx")注解

13 MultipartFile对象文件上传
13.1 FileServiceImpl.uploadFiles上传多张图片，多次入库，加上事务
@Transactional(rollbackFor = {Exception.class})
13.2 FileVo.createTime时间上加上@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")注解
yml配置jackson
spring:
  jackson:
    time-zone: GMT+8
    date-format: yyyy-MM-dd HH:mm:ss
@DateTimeFormat(pattern = "yyyy-MM-dd") //入参时间格式化
@JsonFormat(pattern = "yyyy-MM-dd") //出参时间格式化

MultipartFile文件输出代码：
String path = "D:/qxn/file/testFileFolder/"+fileName;
File fileObject = new File(path);
fileOrigin.transferTo(fileObject);
```


---
```
待整理：
@Slf4j 日志框架
通过拦截器做权限（类似登录验证）
权限框架
springboot定时器实现方式：quartz，@Scheduled 
httpClient
rocketMQ等队列
websocket等连接
netty nio框架
消息推送模块:使用netty+websocke+rabbitmq完成消息的推送
socket
异常代码类
mybatisPlus分页插件
yml插件环境区分
dubbo+zookeeper
shiro
多线程编程
设计模式
搜索服务框架Elasticsearch
Kafka
k8s
DB2，MongoDB,oracle
MyCat
Spring Security+CAS进行用户信息的认证和授权
高并发访问
设计微服务架构满足高并发和高可用架构，高峰QPS/TPS 达8000+，满足高吞吐量和低延迟要求
于euraka和hystrix已经闭源，后续升级技术选型为 spring cloud-alibaba技术体系

其他表：
角色表，菜单表，部门表，权限表，（用户角色多对多），（角色权限多对多）
主表，码表，gis表

springcloud
apollo注册中心

后续补充：
学习补充其他后端需要框架
面试要求的技术
网上看到的流行的新技术
```
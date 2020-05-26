### springboot02
#### 后台管理系统，springboot+shiro+aop+vue
#### 配套前端frontEnd02(vue-elementUI-sass)
```
概要：

```
---
```
流程：
1.导入数据库
2.utf-8编码，jdk版本，maven配置
3.jar包方式
4.添加依赖
5.application.yml配置文件
6.创建PageController.java
7.entity=>dao=>xml=>test=>vo=>service=>serviceImpl
=>ServiceException异常类=>LogsServiceTests
=>JsonResult=>LogsController=>GlobalExceptionHandler
=>frontEnd
7.1开启nginx反向代理，跨域
8.menus菜单表，roles角色表，roles_menus角色菜单关联表
9.删菜单数据之前，有子菜单不允许删除，
删除前先把角色菜单关系表中相应菜单id的数据全删了
10.RolesMenusDao
11.中间表不需要业务层，中间表业务处理在菜单业务层中做
12.拷贝顺序：entity=>dao=>mapper.xml=>service=>
serviceImpl=>controller

13.数据表关联关系
部门（自关联，为了查parentName）
菜单（自关联，为了查parentName）
用户-部门（多对一）
用户-日志（一对多）
用户-角色（多对多）
角色-菜单（多对多）

14.serviceImpl层涉及多张表时，先删关系数据表，再删自身
15.添加角色时，先保存角色信息，再保存角色菜单关系信息
16.添加角色菜单关系信息时，需要rolesId。所以在执行insert角色表时，
useGeneratedKeys="true"获取insert的主键值， 
将它keyProperty="id"赋值给Roles实体类中的id属性。
这样RolesMenusMapper中insertObjects时可以获取Roles的rolesId

17.update的回显，findRolesMenusByRolesId查询，
通过resultMap.result和resultMap.collection，
将两表数据映射到vo对应属性中
18.update roles_menus关系表时，先删再插入
19.删的时候先删关系表，再删自身。插入修改的时候先改自身，再改关系表

20.用shiro给密码加密，
20.1 生成盐值 String salt = UUID.randomUUID().toString();
20.2 用shiro的SimpleHash进行md5加密
SimpleHash sh = new SimpleHash(
	"md5", //algorithmName算法名称
	sourcePassword, //source原密码
	salt, //salt盐值
	1//iterations加密此数，可以对加密结果进行再加密
);
20.3 把加密过的密码和盐值存入vo中存入数据库
userDeptRoleIds.setSalt(salt);
userDeptRoleIds.setPassword(sh.toHex());

21.还有一种查两表数据的封装方法，
把两个dao查询结果放在一个serviceImpl中的Map<String,Object>中，
Map<String,Object> map = new HashMap<>();
map.put("user",user);
map.put("roleIds",roleIds);
return map;

22.aop
oop面向对象编程，自定义的aop优先级是最低的
aop面向切面编程，在对象运行过程中做功能扩展

没有aop靠子类继承对父类进行扩展，或者组合方式

aop：日志处理，权限控制，事务控制，缓存处理

aop大多应用在业务层

aop原理，代理机制
cglib代理继承方式进行功能扩展
jdk动态代理组合方式进行功能扩展

aop2.x默认cglib代理（不需要实现接口），早期默认是jdk（需要有实现接口）
使用jdk动态代理：
在配置文件中spring.aop.proxy-target-class=false

aspect横切面对象，具体类，借助@Aspect声明
joinpoint连接点，切入点
pointcut切入点，多个连接点的集合
advice通知，如around,before,after

步骤：
22.1引入依赖spring-boot-starter-aop
aop有 原生和第三方（AspectJ）
22.2测试切面实现，写切面对象com.qxn.pj.common.aspect.TimeAspectTest
22.3注解方式，写注解com.qxn.pj.common.annotation.RequiredTime
在impl方法上用注解调用

23.利用aop实现日志的添加
写com.qxn.pj.common.aspect.LogsAspect切面对象
写com.qxn.pj.common.annotation.RequiredLog注解对象
@RequiredLog("...")调用

24.jp.getSignature()获取方法签名，
jp.getSignature().getName()获取方法名
目的：获取方法上的注解中的值
1.获取注解
2.获取方法
3.通过Impl实现类的字节码对象Class<?>来获取方法
Class<?>.getDeclaredMethod(name,parameterTypes)

name就是jp.getSignature().getName()

获取parameterTypes：
jp.getSignature()中没有获取parameterTypes的方法
把Signature转向它的接口，看看接口中的其它方法
Signature s = jp.getSignature();
1.打断点，s指向内部类MethodSignatureImpl
2.ctrl+shift+t 打开所有仓库类
3.输入MethodSignatureImpl，打开这个类看一下
4.MethodSignatureImpl实现了MethodSignature接口
MethodSignature继承了CodeSignature
CodeSignature继承了MemberSignature
MemberSignature继承了Signature
用接口接收，
MethodSignature ms = (MethodSignature)jp.getSignature();
有ms.getParameterTypes()，就是我们需要的parameterTypes
jp.getTarget().getClass()//是UsersServiceImpl class
jp.getTarget().getClass().getDeclaredMethod(name,parameterTypes)
jp.getTarget().getClass().getDeclaredMethod(ms.getName(),ms.getParameterTypes())
获取到Method对象
targetMethod.getAnnotation(RequiredLog.class).value();
获取到注解值

25.利用IPUtils工具类获取ip地址
IPUtils.getIpAddr()

26.事务
声明式事务
业务层，类或方法上加@Transactional，默认事务优先级高，
优先级在切面类上通过Order(1)设置
保证其中有一个失败（运行时异常）就回滚
见MenusServiceImpl.deleteObject

MenusServiceImpl中所有方法都要事务控制，就加在类上

mybatis通过sqlSession中的connection连接对象控制事务
spring@Transactional，
通过DataSourceTransactionManager控制事务
.doBegin()开启事务
.doCommit()提交事务
.doRollback()回滚

事务控制使用的是一个连接
3个人操作3三个线程，开启3个事务，3个连接
1个线程用ThreadLocal保证1个connection
connection不能共享
默认@Transactional(readOnly = false)，并发性小
多个线程不能同时操作，比如修改，
但是可以同时读@Transactional(readOnly = true)
//超时时间30s，哪些错误回滚，哪些不回滚，隔离级别
@Transactional(timeout=30,rollbackFor=Throwable.class,
noRollbackFor=IllegalArgumentException.class,
isolation=Isolation.READ_COMMITED)

事务的传播特性
不同业务（service）对象中事务方法的调用
Propagation.REQUIRED(常用，默认)，用当前事务
a调b：
ab没有事务，a开一个事务
a没有事务，b有事务，a开事务，b事务不执行
ab都有事务，b事务不执行

当查询菜单列表时，
@RequiredLog("查询菜单")//aop增加日志
@Transactional(readOnly = true)//查，所以开启只读事务（高并发）
此时会报错，
因为在查询菜单中调用了新增日志方法，不被允许
运行流程：
$Proxy（代理对象调）=>切面（事务、日志）=>MenusServiceImpl（目标对象）
切面链中：
事务aop=>日志aop，
默认传播特性是Propagation.REQUIRED(常用，默认)，用当前事务，
事务aop是只读的，延伸到新增日志的时候会出错

解决办法：
1.将日志aop优先级设置比事务aop高，
这样事务的只读传播特性就不会影响到日志aop
2.将LogsServiceImpl类上的insertLog事务传播特性，设为新事务
如果有当前事务，挂起当前事务开启新事务
@Transactional(propagation = 
Propagation.REQUIRED_NEW)
insertLog(){}
写日志是自定义aop实现的，优先级低，又是modified方法，
所以要开启新事物操作

27.aop切面缓存
UsersServiceImpl.findPageObject查用户列表
其中有查询用户记录数，和用户分页查询
数据层缓存，第二次查用户记录数直接读mybatis二级缓存

业务层缓存，第二次执行findPageObject方法查用户列表，
直接从业务层缓存中取：
在启动类上加上@EnableCaching，
在方法上加@Cacheable(value = "usersCacheAll")
方法的返回值存储到cache中，默认key为方法实际参数的组合

@Cacheable属性：
value:cache对象名称

更新数据时，同时也要更新缓存，防止查到旧数据，有两种方法
1.更新缓存@CachePut
@CachePut(value = "usersCacheById",...)
将查到的返回值记录到缓存中，方法返回值得 与 缓存方法返回值一致

2.清除缓存@CacheEvict
@CacheEvict(value = "usersCacheAll",...)

//selectById时，用指定key清除缓存
@CacheEvict(value = "usersCacheById",
		key = "#userDeptRoleIds.id")
		
//selectAll时，用allEntries = true清除value中所有缓存，
@CacheEvict(value = "usersCacheAll",
		allEntries = true)		
beforeInvocation = false默认更新成功后，再清除缓存

28.shiro apache推出的安全框架，认证，授权
原理：客户端向服务端请求=>过滤器
流程：
用户访问=>身份认证：
	通过=>权限控制：
		有权限能访问
		无权限抛出异常
	未通过回到登录页面
	
shiro架构：	
Subject,SecurityManager,Realm

Subject主体对象，封装认证信息（如用户名、密码、指纹、刷脸）
提交给SecurityManager安全管理器，完成认证和授权操作
Realm查询数据库，可以调用dao，提交给SecurityManager
SecurityManager负责比对

SecurityManager详细架构：
1.Autenticator认证管理器
2.Authorizer授权管理器
3.SessionManager会话管理器，记录用户信息，已登录的依据
4.CacheManager，保存用户权限，提高查询性能
5.pluggable realms对接数据层
6.cryptography加密对象

shiro认证拦截实现：
1.添加shiro依赖，shiro-spring
2.shiro核心对象配置
com.qxn.pj.common.config.SpringShiroConfig配置类
配置SecurityManager对象，shiro核心管理器
配置ShiroFilterFactoryBean对象，对请求信息进行过滤：
	前端静态资源允许匿名访问
	其它资源要认证
将SecurityManager注入ShiroFilterFactoryBean
3.创建SpringWebConfig类，即web.xml中filter配置，
进行spring过滤器配置，
将ShiroFilterFactoryBean注入到
spring的FilterRegistrationBean中
完成shiro的初始化操作

认证失败调用，返回登录页方法
fBean.setLoginUrl("/users/returnToLogin");
返回给前端登录失效json，前端控制路由跳回登录页

浏览器第一次请求，服务器会set-cookie:jsessionId到浏览器，
服务器发现浏览器没带jsessionId这个cookie，会再生成一个set给浏览器

每次请求都会对比jsessionId，不一致会跳回登录页

现在springboot内置了FilterRegistrationBean这个对象，
SpringWebConfig这个类可以不要了，

用actuator查看系统底层是否注册这个bean:
1.依赖spring-boot-starter-actuator
2.application.yml配置加actuator配置
3.点boot dashboard中的项目，再点击
window-show view-other-properties打开视图
在beans中搜索bean名称

认证操作：
UsersController=>
subject.login(token)，token封装的username和password
=>提交给Security Manager的子类DefaultWebSecurityManager
=>找Realm，通过Realm访问数据库=>写ShiroUserRealm实现Realm接口
=>把ShiroUserRealm实现Realm注入到DefaultWebSecurityManager
=>此时DefaultWebSecurityManager有了用户提交的信息，和数据库取的信息
=>将信息传给authenticate()验证

可以把ShiroUserRealm看成一个普通的service，调UsersDao

实例：
1.写UsersDao方法，基于用户名查到用户对象
2.写realm.ShiroUserRealm继承AuthorizingRealm
3.控制层UsersController.doLogin(@RequestBody Users user)
4.登录成功，前端路由跳转

退出登录：
1.在SpringShiroConfig.ShiroFilterFactoryBean中添加
cMap.put("/users/returnToLogin","logout");
2.前端调"/users/doLoginOut"接口，后台匹配到会自动执行"logout"操作，
调用fBean.setLoginUrl("/users/returnToLogin");方法

登录错误的统一异常处理：
com.qxn.pj.common.web.GlobalExceptionHandler

授权：
shiro授权标识格式："sys:user:valid"
"一级菜单名称:二级菜单名称:三级菜单名称"
1.UsersServiceImpl.updateValidByUserId上
加@RequiresPermissions("sys:user:valid")
告诉shiro授权检测，访问方法需要"sys:user:valid"权限
	1."sys:user:valid"提交给shiro框架
	2.基于用户找到用户的权限信息
	
LogsServiceImpl.deleteObjects上也
@RequiresPermissions("sys:log:delete")

在Security Manager中的Authorizer对用户的权限信息进行检测
当方法上加了@RequiresPermissions注解时检测
aop实现

2.SpringShiroConfig中追加：
生命周期LifecycleBeanPostProcessor，
代理对象创建器DefaultAdvisorAutoProxyCreator，
配置Advisor对象，AuthorizationAttributeSourceAdvisor
3.dao实现
UserdRolesDao.findRoleIdsByUsersId()
RolesMenusDao.selectMenusIdsByRoleIds()
MenusDao.findPermissionsByMenuIds()

Mapper.xml实现
RolesMenusMapper.selectMenusIdsByRoleIds
MenusMapper.findPermissionsByMenuIds()

4.在ShiroUserRealm.AuthorizationInfo中调用dao
查出permissions List<String>
注入权限
return new SimpleAuthorizationInfo()
.setStringPermissions(permissionsSet);

比如在运行
@RequiresPermissions("sys:log:delete")
LogsServiceImpl.deleteObjects()方法时，
就会比对当前用户的Permissions中是否包含"sys:log:delete"

29.前端=>后台流程：
浏览器=>filterChain(过滤规则，SpringShiroConfig)=>
DispatcherServlet(spring的)=>interceptor chain(拦截器)
=>Handler(controller)

30.shiro缓存配置
授权操作时，每次都会查询数据库的用户权限信息，
可以将第一次查询的用户权限信息进行缓存，以后登录授权时从缓存中读取

SpringShiroConfig中
1.添加shiro缓存管理器对象，cacheManager，
对用户权限信息进行缓存操作
2.将缓存管理器注入securityManager对象

后面再进行登录时，
就不会执行ShiroUserRealm.doGetAuthorizationInfo方法了，
从业务层设置了缓存

31.shiro rememberMe
用户请求doLogin时，通过认证后，
服务端把加密用户信息加密成的rememberMe这个cookie给浏览器，
Set-Cookie存在浏览器的请求的域名下，关闭浏览器，下次请求时，浏览器会带上rememberMe，
服务端拿到rememberMe这个cookie，能直接访问user匹配的路由，不会比对jsession值

前端：
登录时提交，用户名、密码、isRemember值

后台：
1.在UsersServiceImpl.doLogin中，设置token的
如果isRemember=true;
UsernamePasswordToken token = 
new UsernamePasswordToken();
token.setRememberMe(true);

2.SpringShiroConfig中
添加cookieRememberMeManager
将cookieRememberMeManager注入securityManager

3.修改shiro的过滤级别
shiroFilterFactoryBean中
cMap.put("/**", "authc");改为
cMap.put("/**", "user");

chrome查看cookie
chrome://settings/siteData
"rememberMe"中是用户信息subject对象的加密

32.shiro会话时长配置
shiro用户认证之后，会将用户信息写入服务端session中，默认时长为30分钟，
自定义配置：
SpringShiroConfig中
1.添加session管理器对象
SpringShiroConfig.sessionManager
2.将sessionManager注入securityManager

33.从session中获取用户信息
1.登录时填入用户名，密码封装成UsernamePasswordToken，
传入Subject.login(token)
2.Subject.login会调用ShiroUserRealm.doGetAuthenticationInfo认证方法，
doGetAuthenticationInfo方法里，将传入的token取出用户名，
根据用户名查users表的user po对象，放入SimpleAuthenticationInfo对象中
3.在session有效期中，通过SecurityUtils.getSubject().getPrincipal()，
能取出登录的user po对象，使用
场景：
4.动态用户禁用启用修改对象
UsersController.updateValidByUserId中取出user.getUsername()，
users.setModifiedUser(user.getUsername());
5.动态日志用户名LogsAspect.insertLog中，
new Logs().setUsername()
6.//获取整个session对象
SecurityUtils.getSubject().getSession()
7.将(Users)SecurityUtils.getSubject().getPrincipal();提取出去
com.qxn.pj.common.util.ShiroUtils.getUser()

34.修改密码
旧密码，新密码，密码确认
1.验证：原密码不能为空，新密码不能为空，密码输入不一致
//取登录的对象
(Users)SecurityUtils.getSubject().getPrincipal();

//数据库中的 加密密码 SimpleHash类型
SimpleHash sh = new SimpleHash("md5", // algorithmName算法名称
				originPassword, // source原密码
				UUID.randomUUID().toString(), // salt盐值
				1// iterations加密此数，可以对加密结果进行再加密
				);
2.密码对比
输入的旧密码取登录用户的盐值加密后  和  登录用户的加密密码 对比，旧密码是否相同
输入的新密码取登录用户的盐值加密后  和  登录用户的加密密码 对比，新密码和旧密码是否相同
3.把新密码，新盐值，根据userId update入数据库

35.获取登录用户信息
UsersController.getLoginUser
Users user = ShiroUtils.getUser();

36.spring拦截器
HandlerInterceptor接口
preHandle//控制层方法执行前，返回值true,false代表拦截和放行
postHandle//控制层方法执行后
afterCompletion//视图解析完成后

1.写拦截器TimeAccessInterceptor，时间访问拦截，什么条件下放行，什么条件下拦截

2.SpringWebConfig实现WebMvcConfigurer接口
addInterceptors方法，添加拦截器，设置拦截路径

代码执行过程：
Filters(过滤器)
DispatcherServlet(前端控制器，本质是servlet)
HandlerInterceptors(拦截器，拦截controller)
Handler(后端controller)
aop(aspect切面)
service

为什么不用filter和aop，用拦截器：
filters用于共性问题，如对所有post请求进行编码处理
HandlerInterceptors对某一个controller控制
aop反射机制创建代理对象，性能低一些

HandlerInterceptors是基于回调机制，比aop快

数据层也有拦截器
mybatis.Interceptor

aop也有拦截器
spring.MethodInterceptor

shiro也有拦截器
shiro.MethodInterceptor

37.将同步写日志，改成异步写日志
springboot @Async

场景：
@RequiredLog("查询菜单")//写日志
MenusServiceImpl.findObjects(){
	//查询菜单...
}
写日志和查询菜单是同步

现在加大写日志的耗时操作，
在LogsServiceImpl.insertLog中加入，
Thread.sleep(10000);

会发现写完日志前，无法执行查询菜单

解决方案：
1.将LogsAspect.insertLog中的
logsService.insertLog(entity);放入一个新的线程
new Thread() {
	public void run() {
		logsService.insertLog(entity);
	}
}.start();
写日志和查菜单变成2个线程，线程名：
findMenus.thread.name = http-nio-84-exec-2
insertLog.thread.name = Thread-48

缺陷：并发量小
100万人访问menu路径，会创建100万个线程，内存溢出

2.线程池，spring异步写
1.@Async//加注解，用异步方式写日志，这边得没有返回值
public void insertLog(){}

2.@EnableAsync//启用spring中的异步配置
public class SpringBoot02Application {}
线程名：
findMenus.thread.name = http-nio-84-exec-7
insertLog.thread.name = task-1

缺点：每次写日志都是一个新的线程，所以需要线程池

可以在第三方库里加断点

@Async默认池对象是ThreadPoolTaskExecutor.submit()
//ThreadPoolTaskExecutor核心线程8个，小于8创建新线程，大于8任务放在队列中

自定义池对象
SpringAsyncConfig中
1.重写AsyncConfigurer接口的getAsyncExecutor方法，
用旧版的SimpleAsyncTaskExecutor
线程名：
findMenus.thread.name = http-nio-84-exec-6
insertLog.thread.name = SimpleAsyncTaskExecutor-1

2.重写AsyncConfigurer接口的getAsyncExecutor方法，
用新版的ThreadPoolTaskExecutor
@Async调用
线程名：
findMenus.thread.name = http-nio-84-exec-6
新线程未设置线程名前缀
insertLog.thread.name = ThreadPoolTaskExecutor-1
新线程设置线程名前缀
insertLog.thread.name = qxn-service-thread-1

3.SpringThreadPoolConfig中
不实现AsyncConfigurer接口，自定义方法asyncExecutor给spring管理
@Async("asyncExecutor")调用
线程名：
findMenus.thread.name = http-nio-84-exec-6
新线程未设置线程名前缀
findMenus.thread.name = pool-1-thread-1
新线程设置线程名前缀
insertLog.thread.name = qxn-server-thread-name-1

将SpringThreadPoolConfig的配置参数写到application.yml中

1.SpringThreadPoolConfig中
在属性上添加spring的@Value("${async-thread-pool.corePoolSize}")注解

2.SpringThreadPoolConfig中
@ConfigurationProperties("async-thread-pool")调用
会调用SpringThreadPoolConfig属性中的set方法
添加spring-boot-configuration-processor依赖
添加lombok.@Setter注解

@Async可以写在方法和类上

当@Async有返回值时
@Async
public int insertLog(){
	int rows = logsDao.insertLog(entity);
	return rows;
}
直接返回会报错，因为这是一个异步操作，不知道何时能够结束

得写成
@Async
public Future<Integer> insertLog(){
	int rows = logsDao.insertLog(entity);
	return new AsyncResult<Integer>(rows);
}
取值时写成：
Future<Integer> insertLog = logsService.insertLog(entity);
Integer rows = insertLog.get();

38.多数据源配置
例如将日志表放入另一个数据库中

39.第三方缓存配置
例如将业务层数据进行缓存

40.项目运行分析
请求=>filter过滤器=>DispatcherServlet(前端控制器)=>
HandlerInterceptor(拦截器)=>Controller(后端控制器)=>...

41.项目打包
jar包（推荐）
1.run as=>maven install
2.show in=>system explorer=>target目录下，
找到SpringBoot02-0.0.1-SNAPSHOT.jar
3.运行，java -jar .\SpringBoot02-0.0.1-SNAPSHOT.jar
4.服务就跑起来了

war包
1.建war包项目
<packaging>war</packaging>
2.不打包tomcat
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
3.修改启动类，继承SpringBootServletInitializer
@SpringBootApplication
public class SpringBoot02Application extends 
SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02Application.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(
	SpringApplicationBuilder builder){
		builder.sources(SpringBoot02Application.class)
	}
}
4.打包run as=>maven install
5.show in=>system explorer=>target目录下，
找到SpringBoot02-0.0.1-SNAPSHOT.war
6.将SpringBoot02-0.0.1-SNAPSHOT.war复制到，tomcat/webapps/下，
启动/bin/startup.bat运行
7.访问路径为localhost:端口号/项目名称
```

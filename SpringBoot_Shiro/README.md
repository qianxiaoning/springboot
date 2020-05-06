### springboot_shiro
#### springboot+shiro+html(页面、js，fetch动态加载)
```
概要：

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
7.entity=>dao=>xml=>vo(JsonResult)=>service=>serviceImpl
=>ServiceException异常类=>LogsServiceTests
=>JsonResult=>LogsController=>GlobalExceptionHandler
=>frontEnd
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
在方法上加@Cacheable
方法的返回值存储到cache中，默认key为方法实际参数的组合

@Cacheable属性：
value:cache对象名称

更新数据时，同时也要更新缓存，防止查到旧数据
更新usersCache缓存对象中userDeptRoleIds.id的值，
方法返回值得 与 查方法返回值一直
@CachePut(value = "usersCache",
key = "#userDeptRoleIds.id")

使指定id的cache失效，
beforeInvocation = false默认更新成功后再清缓存
@CacheEvict(value = "usersCache",
		key = "#userDeptRoleIds.id",
		beforeInvocation = true)
allEntries = true，cache全清		

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
```

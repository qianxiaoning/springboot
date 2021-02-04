### springbootQuickStart01
#### springboot脚手架01
- 结构：
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
redisUtil.set("userDoRedisUtil",userDo);
UserDo userDoRedisUtil = (UserDo) redisUtil.get("userDoRedisUtil");
redisUtil.hset("a", "a", "b");
redisUtil.hget("a", "a");
redisUtil.expire("userDoRedisUtil",1000);
redisUtil.hasKey("userDoRedisUtil");
redisUtil.del("userDoRedisUtil");

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
dao层 extends BaseMapper<Do>
entity上： 
@TableName("user")，
@TableId(value = "id", type = IdType.ASSIGN_ID)//雪花算法自动生成id
@Version//乐观锁，使用mybatisplus进行改删操作时触发
@TableLogic//表字段逻辑处理

4 普通注册
4.1 密码加密保存 
param.setPassword(JasypUtil.encryptWithSHA512(param.getPassword()));
4.2 调用mybatisplus的雪花算法
Long id = IdWorker.getId(userDo1);
4.3 ServiceUtil.createEntity反射给实例设置属性值
4.4 备选：mybatisPlus自动填充功能
4.4.1 
UserDo：注解填充字段
@TableField(fill = FieldFill.INSERT)
private Date createTime;
@TableField(fill = FieldFill.INSERT_UPDATE)
private Date updateTime;
4.4.2 MybatisPlusConfig实现类MyMetaObjectHandler
重写insertFill、updateFill方法。这样使用mybatisplus insert/update方法时就会自动填充值

5 普通登录
5.1 数据库密码解密与参数密码比较
String password = JasypUtil.decryptWithSHA512(userDo.getPassword());
password.equals(param.getPassword())
5.2 成功登录则，
生成token，
String token = UUID.randomUUID().toString();
token为key，用户信息存入redis，
redisUtil.set("UserToken:"+token,loginVo,7*24*3600);
token存入浏览器一级域名的cookie中，实现cookie在二级域名共享，作用于分布式服务单点登录（一处登录，处处通行）
CookieUtil.addCookie(request, response, "user_token", token,7*24*3600, "localhost");
```
---
```
待整理：
2.日志切面
3.拦截器
4.过滤器
5.quartz
7.mybatisplus
8.httpClient
```
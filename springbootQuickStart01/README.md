### springbootQuickStart01 springboot脚手架01
#### 结构：
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

springBootTest bean测试类：
 .BeanTest
test bean简单测试类：
 .SimpleTest
```
---
#### 过程：
```
libs 第三方库层：
1.redis
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

2.swagger
2.1 pom.xml springfox-swagger2 springfox-swagger-ui
2.2 swagger配置
libs.swagger.SwaggerConfig
2.3 yml配置
2.4 逻辑代码层添加注解
@Api(tags = "用户操作接口")
@ApiOperation("id查找单个用户")
@ApiImplicitParam(name="id",value="用户id",required = true)
@ApiModel(value="user对象", description="")
@ApiModelProperty(value = "用户id",required = true)
2.5 访问
http://localhost:8000/swagger-ui.html

3.mybatisPlus
3.1 pom mybatis-plus-boot-starter
3.2 yml
3.3 逻辑代码层
service层 extends IService<Do>
dao层 extends BaseMapper<Do>
entity 
@TableName("user")，
@TableId(value = "id", type = IdType.ASSIGN_ID)//雪花算法自动生成id
@Version//乐观锁，使用mybatisplus进行改删操作时触发
@TableLogic//表字段逻辑处理
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
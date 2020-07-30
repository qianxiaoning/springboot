### c2c-system(springboot+mybatisPlus+PowerDesigner+MultipartFile+nginx+linux+redis+aop+HttpClient+jsonp+sso+dubbo+zookeeper+Cookie+Interceptor+ThreadLocal+Quartz)
#### Consumer to Consumer，个人对个人平台系统
#### 配套前端c2c-system_backgroundSystem_frontEnd(vue),c2c-system_web_frontEnd(vue)
```
概要：
1.c2c电商项目：
1.1.分布式：
多个项目，一个服务器宕了，其它仍能正常访问
垂直拆分：
根据功能模块，将业务拆分到不同的服务器中
日志管理（独占tomcat服务器）
角色管理（独占tomcat服务器）
水平拆分：
日志管理中：controller,service,dao
先垂直，后水平

1.2.集群实现高并发：
单台tomcat服务器并发量：180-240（理论），实际150/s
搭建服务器集群，实现高并发

1.3.高可用（HA）：
当服务器故障，能实现自动化的故障迁移
从机代替主机，心跳检测机制

1.4.海量数据处理：
大数据

1.5.安全性：
网络安全，程序安全

2.idea搭建聚合项目：
parent项目：
1.New Project-Spring Initializr-c2c-system-type:Maven POM-Packaging:Jar
2.src目录删除
3.添加parent项目依赖，引入所有需要的jar包

子jar包项目：
parent项目-右键New Module-maven-skip archetype

子war包项目：
1.parent项目-右键New Module-maven-skip archetype
2.在pom.xml中添加
<packaging>war</packaging>
添加插件spring-boot-maven-plugin
3.生成"src\main\webapp\WEB-INF\pages\"文件夹
打开Project Structure-Project Settings-Modules-选中子war包项目-+Web-添加两个路径
4.在parent pom.xml中添加war包项目需要的dependency依赖
```
---
```
流程：
1.新建c2c-system pom项目，添加所有所需依赖
2.c2c-system-common，工具类项目
3.c2c-system-background_system_war，war包项目测试
生成"src\main\webapp\WEB-INF\pages\"文件夹
引入common依赖
添加插件maven-surefire-plugin，跳过测试类
4.添加c2c-system-common，工具类
BasePojo,Item
5.c2c-system-background_system_war添加启动类C2cSystemWarProTestApplication
6.修改项目启动类配置，将Working directory指定为，聚合项目的war包子项目
7.测试http://localhost:8071/test，war包子项目跳转jsp没问题
8.后面c2c-system-background_system_war只需要提供接口即可
9.PowerDesigner图形界面管理数据表，自动生成sql语句，数据建模工具
物理模型图，展现表关联关系c2c-system_PhysicalDataModel.pdm
10.开启nginx反向代理，配置nginx.conf代理关系、hosts域名映射关系
主页域名 manage.c2c-system.com
反向代理：由代理者代替用户访问真实资源，特点：用户不需知道真实资源的位置
nginx优点，低内存，高并发
11.ProductCategoryController.findProductCategoryList
controller中，@RequestParam注解，
(@RequestParam(value = "id",defaultValue = "0",required = false) Long parentId){}
表示，前端传入id，后台转为parentId接收，默认值为0
12.SysResult中
success(String msg) 
success(Object object)会冲突，所以一般不用success(String msg)
13.GlobalException全局异常类，mapper=>service=>controller，最后错误都会汇聚到controller中
14.ProductController.deleteProduct(Long[] ids);
//前端传入ids:"1,2,3",在controller中用Long[]接收，会自动拆分成[1l,2l,3l]
15.数组转集合，List<Long> idList = Arrays.asList(ids);
16.ProductServiceImpl.saveProduct，product.id主键自增后回填主键信息
17.ProductController.findProductDescById
//restful风格接收参数，@PathVariable注解取参数
@GetMapping("/query/product/desc/{id}")
public SysResult findProductDescById(@PathVariable Long id){
18.图片、文件上传接口，spring.MultipartFile
测试FileController.fileTest
19.图片vo对象，ImageVO
ImageVO.url返回图片虚拟地址（线上地址）
20.批量上传图片，FileController.imageUpload
21.FileController.imageUpload.urlDirPath，图片的虚拟地址（线上路径）
使用nginx反向代理，配合hosts本地域名映射，将磁盘文件夹路径映射到，image.c2c-system.com域名
22.将FileController.imageUpload磁盘路径，和线上路径，动态写入image.properties
localDirPath，urlDirPath
23.提高并发能力，windows系统中搭建tomcat服务器集群
23.1.将c2c-system项目打包，修改application.yml端口号，:8061,:8062,:8063，
打包：Maven-项目名.Lidecycle.clean-项目名.Lidecycle.install
23.2.将三个war包放入某个文件夹，cmd:java -jar ...war启动三个war包
23.3.搭建nginx代理服务器，实现负载均衡，同一个域名，访问不同的tomcat服务器
见nginxConf/nginx.conf
负载均衡策略：轮询、权重、IPHASH
23.4.PortController端口测试controller，访问http://manage.c2c-system.com/api/port，看当前端口号
24.nginx其余属性
24.1.down属性
nginx集群时，如果某一台服务器故障，等到超时时间后，会访问下一台服务器
通过nginx.conf指定某台服务器宕机，则nginx不会再次访问
upstream c2cSystemWindows {
   server 127.0.0.1:8061 down;
   server 127.0.0.1:8062;
}
24.2.backup属性
设为备用机，当主机宕机或者遇忙时，备用机生效
upstream c2cSystemWindows {
   server 127.0.0.1:8061 backup;
   server 127.0.0.1:8062;
}
24.3.nginx心跳检测机制
tomcat服务器在指定时间未响应，会在内存中标识为down
# max_fails最大失败数，fail_timeout规定时间不会再访问故障机
upstream c2cSystemWindows {
   server 127.0.0.1:8061 max_fails=1 fail_timeout=60s;
   server 127.0.0.1:8063;
}
25.准备linux环境
25.1.安装VMware，导入linux文件-dsCentOS.vmx
25.2.选中dsCentOS-empty，开启此虚拟机，我已复制该虚拟机
25.3.linux终端ifconfig，eth2: inet addr:192.168.89.128，正常显示ip，完成
选中dsCentOS-empty，开启此虚拟机，我已复制该虚拟机
25.4.选中系统，电源-关机
修改内存：1G
网络适配器-网络连接（解释）
桥接模式：虚拟机直接连入当前局域网，分配独立ip，局域网中ip都可访问此虚拟机
NAT模式：在本机中开辟一块私有网络空间，给开启的虚拟机分配ip，192.168.xxx.xxx
本机通过VMnet8和虚拟机通信，局域网其它机器不能连入虚拟机，更安全
25.5.克隆系统文件
选中系统，管理-克隆-虚拟机当前状态-创建链接克隆（保留公共部分，只提取修改部分）-选择路径、修改文件名-开启此虚拟机

ifconfig查看linux ip：
windows机器VMnet8网卡，ip地址192.168.89.1
虚拟机ip，192.168.89.129
同一网段连接

25.6.配置虚拟机的静态ip，防止ip改变
VMware-两台电脑-VPN Connections-Configure VPN-Auto eth3-edit-IPv4 Settings-Method:Manual
-add Address:
Address/Netmask子虚掩码（校验）/Gateway网关（出口）
192.168.89.129/255.255.255.0/192.168.89.2
DNS servers 192.168.89.2

26.XShell远程连接工具
26.1.连接虚拟机，名字、ip、账号、密码
26.2.文件-打开，多建立几个连接
27.项目部署到linux服务器，nginx集群ip地址修改
27.1.安装lrzsz插件，拖拽文件上传
27.2.cd /usr/local/src/
27.3.将jdk1.8安装包传入
解压tar -xvf jdk-8u51-linux-x64.tar.gz
删除压缩包，rm -f jdk-8u51-linux-x64.tar.gz
改名mv jdk1.8.0_51/ jdk1.8
配置环境变量，vim /etc/profile
#set java env下
JAVA_HOME=/usr/local/src/jdk1.8
JAVA_BIN=/usr/local/src/jdk1.8/bin
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export JAVA_HOME JAVA_BIN PATH CLASSPATH
27.4.环境变量生效
source /etc/profile
查看是否安装成功 java -version
27.5.搭建linux集群
linux连接windows数据库，将项目application.yml中的ip地址改为VMnet8网卡ip
url: jdbc:mysql://192.168.89.1:3306/...
将端口改为8061,8062,8063
然后项目打包命名为8061.war,8062.war,8063.war

远程访问权限配置
windows浏览器=>linux tomcat=>windows Mysql，两道操作系统防火墙，Mysql数据库权限
windows防火墙，选择性关闭
Mysql数据库权限：
grant [权限] on [数据库名].[表名] to ['用户名']@['web服务器的ip地址'] identified by ['密码'];
在SQLyog中
grant all on *.* to 'root'@'%' identified by 'root';
关闭linux防火墙
永久关闭防火墙，下次生效 chkconfig iptables off/on
临时关闭linux防火墙，立即生效 service iptables stop/start

在src/下
mkdir tomcats
cd tomcats/
将8061.war传入
java -jar 8061.war运行
java -jar 8061.war & 运行后可继续输入指令
浏览器http://192.168.89.129:8061/port访问测试
8062,8063同样传入，运行

nginx.conf中新增linux集群
upstream c2cSystemLinux 虚拟机ip 192.168.89.129，启用c2cSystemLinux，重启nginx

正常访问http://manage.c2c-system.com/，文件上传接口用不了

28.关闭java -jar 8061.war & 开启的进程
28.1.查询进程
ps -ef |grep 8061.war
ps -ef |grep java*
28.2.结束进程
kill 3183 被使用时，可能不成功
kill -15 3183 正常步骤关闭
kill -9 3183 强制结束

29.添加redis缓存
准备工作：
application.yml文件，端口、数据库url改回windows，启动项目
nginx.conf不启用集群，重启

作用：
为了高并发，做了tomcat服务器集群，但是连接的都是一个数据库，数据库并发压力大
添加redis缓存服务器，降低数据库压力
结构变为：
浏览器=>tomcat服务器=>redis缓存服务器=>数据库

29.1.linux安装redis
切换到工作目录
cd /usr/local/src/
将redis-5.0.4.tar.gz传入
解压tar -xvf redis-5.0.4.tar.gz
改名mv redis-5.0.4/ redis
redis的编译、安装
cd redis/
编译make
安装redis
make install
29.2.redis常规命令
29.2.1.修改redis配置文件
vim redis.conf
注释ip绑定，#bind 127.0.0.1
关闭保护模式，protected-mode no
后台启动，daemonize yes
:wq
29.2.2.常规命令
启动服务redis-server redis.conf
默认端口号 6379
进入redis客户端，redis-cli -p 6379。默认端口时，redis-cli即可
29.2.2.1.String类型：
存数据set key value
取数据get key
获取key长度，strlen key
是否存在(1存在)，exists key
删除del key
查询keys *
以a开头，*n个字符，?一个字符，keys a*
多个复值mset a a b b c c
多个取值mget a b c
追加append a " xxx"
查看类型type a
redis中有16个数据库，数据不共通，默认操作第1个
切换到第16个数据库，select 15
清空当下数据库flushdb
清空所有数据库flushall
加1，incr a
减1，decr a，用于记录访问人数
加100，incrby a 100
指定key生效时间，expire a seconds
指定key失效时间，pexpire a milliseconds
检测key失效时间，-1无失效时间，-2数据不存在，ttl key
取消key失效时间，persist key

windows上浏览器调用：
pom.xml引入redis包redis.clients.jedis,spring-data-redis
测试类：
TestRedis.testString
//setnx()值已经存在，不允许覆盖
jedis.setnx("qxn", "qw");
//赋值和超时同时执行
jedis.setex("time", 100, "超时测试");
//赋值，是否可以覆盖，时间单位，超时时间
//NX不允许覆盖，XX可以覆盖
//EX单位秒，PX单位毫秒
jedis.set("时间","测试是否有效","NX","EX",100);

29.2.2.2.Hash类型：
存储对象{id:1,name:"jack"}
hset user id 1
hset user name jack
hget user id
hget user name
hexists属性是否存在
hdel删除属性
del key删除集合
hgetAll user获取全部键和值
hkeys获取全部keys
hvals获取全部值
hmget获取多个属性
hmset设置多个属性
hsetnx不存在时允许设置
hstrlen获取key的长度

测试类：
TestRedis.testHash

29.2.2.3.List类型：
lpush队列左侧入一个或多个
lpush list 1 2 3
rpush队列右侧入一个或多个
lpop队列左侧出一个或多个
rpop队列右侧出一个或多个
rpop list

测试类：
TestRedis.testList

29.2.3.redis事务命令
multi标记一个事务开始
exec提交
discard回滚

测试类：
TestRedis.testTx

29.3.redis实现商品类目回显
29.3.1.redis等第三方交给spring管理
引入pom依赖
配置类common.RedisConfig
配置文件war_pro_test.redis.properties
思路，java对象UITree =>json =>redis.string

29.3.2.对象转json测试类，TestJSON
通过ObjectMapper对象：
//对象=>json
String json = mapper.writeValueAsString(ProductDesc);
//json=>对象
ProductDesc desc = mapper.readValue(json, ProductDesc.class);
29.3.3.将ObjectMapper对象方法，封装成ObjectMapperUtil
工具类异常处理
try {
    ...
} catch (Exception e) {
    e.printStackTrace();
    throw new RuntimeException(e);//将检查异常，转化为运行时异常，通知开发者
}
ObjectMapperUtil.toObject用泛型转化
29.3.4.redis菜单类目回显，serviceImpl实现
@GetMapping("/list")时，
//用加上缓存的方法
productCategoryService.findUITreeCacheList(parentId);
serviceImpl中逻辑
先查缓存，
无数据，调数据库findUITreeList(parentId);再将结果存入redis缓存，parentId为key
有数据，读redis缓存，parentId为key
29.3.5.aop实现redis菜单类目回显
目的：缓存不要和业务逻辑写在一起，降低耦合
29.3.5.1.自定义注解CacheFind
29.3.5.2.在findUITreeList上加上@CacheFind注解
29.3.5.3.写切面CacheAspect.around
自定义key名，包名.类名.方法名::parentId
29.4.redis持久化
redis运行在内存中，默认关闭后会持久化到硬盘上
rdb模式（默认）快照空间小，效率高
默认名dump.rdb（定期实现持久化，数据可能会丢失）
持久化命令：
save 立即持久化，阻塞其它操作，同步，默认
bgsave 后台启动持久化，异步

redis.conf中持久化配置：
save 900 1，900s内执行1次操作，持久化1次
save 300 10，300s内执行10次操作，持久化1次
save 60 10000，60s内执行10000次操作，持久化1次

dbfilename dump.rdb默认文件名

aof模式（异步，可以实时持久化，效率低）
记录用户的操作过程，文件较大

配置：
appendonly no
appendfilename "appendonly.aof"默认文件名

aof策略
appendfsync always 用户一更新就持久化
appendfsync everysec 每秒持久化一次（效率高）
appendfsync no 持久化操作，交给操作系统

数据要求高：aof，要求低就默认rdb

29.5.redis内存策略
redis数据在内存中，不清会溢出，所以需要定期清空数据
内存优化策略
lru算法，最近最少使用，用字段t记录访问时间，淘汰t值最大的
29.5.1.volatile-lru，在设定超时时间的数据中使用lru算法，删除数据
29.5.2.allkeys-lru，所有数据都使用lru算法，删除数据
lfu算法，最不经常使用，记录引用次数
29.5.3.volatile-lfu，在设定超时时间的数据中使用lfu算法，删除数据
29.5.4.allkeys-lfu，所有数据都使用lfu算法，删除数据
29.5.5.volatile-random，在设定超时时间的数据，随机删除
ttl算法，根据剩余时间删除
29.5.7.volatile-ttl，在设定超时时间的数据中，根据剩余时间删除
29.5.8.noeviction，不会自动删除数据，内存溢出报错返回（默认）

修改redis内存策略
redis.conf:
maxmemory-policy xxx

29.6.redis分片机制
扩大内存和执行效率
3个g空间，分给3个redis服务器，一人1个g，提高效率
6379,6380,6381
准备3台：
在redis目录中：
mkdir shards
准备3份配置文件
cp redis.conf shards/6379.conf
cp redis.conf shards/6380.conf
cp redis.conf shards/6381.conf
区分端口：
:/port回车
port 6380
port 6381

启动3台redis
redis-server 6379.conf
redis-server 6380.conf
redis-server 6381.conf

检查服务是否正常
ps -ef |grep redis

redis开始分片
测试类TestRedis.testShards
new ShardedJedis(JedisShardInfoList);
三台redis存的数据都是不同的

hash一致性算法
hash值32位数据，2进制中，2^32种可能
key:value
存数据：服务器将key进行hash计算，均匀分配到各节点中
取数据：拿到key，计算hash，找到最近节点
不是随机存储，有规则

hash一致性的特性
均衡性：
节点都是计算hash划分的，可能分布不均，导致数据命中率低
此时会引入虚拟节点，实现数据平衡

单调性：
节点只能增，不能减
虚拟节点新增时，数据可以自动迁移

分散性：
分布式系统中，某些系统不能使用全部内存空间时，一个key可能导致多个位置

负载：
分布式系统中，某些系统不能使用全部内存空间时，一个位置可能有多个key

让分布式系统尽可能使用全部的内存空间，可以降低分散性和负载的概率

29.7.spring整合redis分片
RedisConfig.shardedJedis
redis.properties配置文件，配置redis.nodes
CacheAspect切片中，注入private ShardedJedis jedis; //分片机制

将redis分片关闭 kill -9 pid pid pid

29.8.redis哨兵
场景：
3个分片，如果1个宕机了，分片就不能用了
保证服务器不宕机
主从机：
6379主机
6380,6381从机

原理：
主从备份，平时6379的数据得时刻向6380,6381同步
用户通过哨兵连接集群主机
哨兵实时和主机进行心跳检测，ping-pong，如果连续3次未受到主机回应，则表示宕机，开始推选
哨兵连接主机时，能获取主机的所有从机信息，选一个从机做主机，其它再次作为从机

步骤：
29.8.1.主从结构
在redis目录下，复制目录并改名
cp -r shards/ sentinel
删备份文件
rm -f dump.rdb appendonly.aof
启动3台服务器
redis-server 6379.conf
redis-server 6380.conf
redis-server 6381.conf
进入redis中info replication看主从role:master
将80，81的主机修改为6379
redis-cli -p 6380
SLAVEOF 192.168.89.129 6379
redis-cli -p 6381
SLAVEOF 192.168.89.129 6379
主从数据会自动同步
从机不能写入数据

29.8.2.配置哨兵
将redis目录下哨兵配置文件sentinel.conf，复制到sentinel/下
cp sentinel.conf sentinel
编辑配置文件
vim sentinel.conf
protected-mode no，注解去掉，保护模式关闭
daemonize yes，开启后台启动

设定哨兵监控sentinel monitor mymaster 192.168.89.129 6379 1
1表示当前哨兵选举的票数，几票生效，涉及选举的设置为奇数

30s主机没响应，开始推选，sentinel down-after-milliseconds mymaster 30000

sentinel failover-timeout mymaster 180000哨兵选举失败时间（新主机上任失败，等待时间）3分钟

哨兵测试：
1.启动哨兵
redis-sentinel sentinel.conf
查看是否正常ps -ef |grep redis
关闭79主机，查看80/81是否变为主机，再次开启79，看79是否变为从
redis-cli -p 6379 shutdown
redis-cli -p 6380
INFO replication 变为主机role:master
重新启动79
redis-server 6379.conf
redis-cli
info replication
在vim sentinel.conf最后，有主从记录

测试类TestRedis.testSentinel

交给spring管理
RedisConfig.jedisSentinelPool导入RedisConfig.jedis，高效
redis.properties配置文件，配置redis.sentinel
CacheAspect切片中，注入private Jedis jedis; //哨兵机制

浏览器访问商品目录，测试

redis分片，实现内存的扩容，没有实现高可用
redis哨兵，实现redis高可用（不怕宕机），没有实现内存扩容，哨兵没有实现高可用

退出redis服务
退出命令行，exit/ctrl+c
关闭redis服务
redis-cli -p 6379 shutdown。默认端口时，redis-cli shutdown即可
关闭多个，kill -9 pid1 pid2

29.9.redis集群（现在最主流的）
redis集群，分片并且每台主机都有对应从机，在内部实现高可用，不需要第三方监控
模拟3主，3从，端口7000~7005

集群通用情况（不适用redis集群），集群数量是奇数台（包含主从）
集群正常工作的条件，存活的服务器数量 > 总数/2
3台 2>1.5 搭建集群的最小数量3台 最多宕机1台

redis主机分片，一台都不能少

步骤：
29.9.1.停掉哨兵相关服务
ps -ef |grep redis
kill -9 10038 10043 10174 10190
29.9.1.进到redis目录下，准备集群文件夹
mkdir cluster
29.9.2.在cluster目录下，创建7000~7005文件夹
mkdir cluster/7000
29.9.3.修改配置文件
cp redis.conf cluster/7000/
cd cluster/
vim 7000/redis.conf
注释本地绑定ip，#bind 127.0.0.1
关闭保护模式，protected-mode no
端口号改为7000，port 7000
后台启动，daemonize yes
修改pid文件位置，pidfile /usr/local/src/redis/cluster/7000/redis.pid
修改持久化文件路径，dir /usr/local/src/redis/cluster/7000/
设定内存优化策略，maxmemory-policy volatile-lru
关闭aof模式，appendonly no
开启集群配置，cluster-enabled yes
开启集群配置文件，cluster-config-file nodes.conf
修改集群超时时间，cluster-node-timeout 15000
29.9.4.复制配置文件
在cluster目录下
cp -r 7000/ 7001
cp -r 7000/ 7002
cp -r 7000/ 7003
cp -r 7000/ 7004
cp -r 7000/ 7005
29.9.5.修改配置文件
vim 7001/redis.conf
:%s/7000/7001/g
:wq
改7001~7005
29.9.6.启动节点
批量启动节点
编写shell启动脚本
vim start.sh
#!/bin/sh
redis-server 7000/redis.conf &
redis-server 7001/redis.conf &
redis-server 7002/redis.conf &
redis-server 7003/redis.conf &
redis-server 7004/redis.conf &
redis-server 7005/redis.conf &

编写shell结束脚本
vim stop.sh
#!/bin/sh
redis-cli -p 7000 shutdown &
redis-cli -p 7001 shutdown &
redis-cli -p 7002 shutdown &
redis-cli -p 7003 shutdown &
redis-cli -p 7004 shutdown &
redis-cli -p 7005 shutdown &

启动脚本
sh start.sh

29.9.7.设定主从规则
到redis/下，
1代表一个从机，一对就2个
redis-cli --cluster create --cluster-replicas 1 192.168.89.129:7000 192.168.89.129:7001 192.168.89.129:7002 192.168.89.129:7003 192.168.89.129:7004 192.168.89.129:7005
[OK] All 16384 slots covered.完成
29.9.8.拍摄快照
VMware(时钟+)，拍摄快照
快照完成，点击快照管理，选中快照，转到
29.9.9.测试
redis-cli -p 7000
info replication从机是7004
redis-cli -p 7000 shutdown 15秒后看7004是否变为主机
redis-cli -p 7004
info replication
启动7000，看是否变为从机
cd cluster/
redis-server 7000/redis.conf
redis-cli -p 7000
info replication
29.9.10.redis集群（就是redis分区）高可用推选原理
redis中所有节点，都会保存当前redis集群中全部的主从状态信息
在每个节点中的nodes.conf文件
半数以上节点认为宕机，则剩余主节点在 宕机的从机们 中选举新主机

连续3次投票没有结果，表示选举失败，会出现脑裂现象。增加主机数量，可降低脑裂发生

redis集群宕机条件，最少超半数，9宕5，可以从其它主机借从机，但是其它主机必须留1个从机
最多9宕7，所有主机的从机都宕了
29.9.11.redis hash槽存储数据原理
redis分片用的hash一致性
redis集群用的hash槽存储
16384个节点等分
key =>hash()算出槽数 =>槽（16384 3等分之一）=>存入key:value
算出槽数可能相同，key名不同

redis分片效率较高：分布运算在服务器上，redis只负责取
redis集群：在redis内部，效率低

测试TestRedis.testCluster

29.9.12.spring整合redis集群
RedisConfig.jedisCluster
redis.properties redis.cluster
CacheAspect private JedisCluster jedis;	//集群

30.前台站点搭建
浏览器=>nginx中转代理=>前台站点=>后台站点=>redis缓存=>数据库
前台站点，远程调用后台站点的业务
用http/json
前台服务器=>[垂直拆分：图片服务器、后台服务器、sso单点登录服务器、购物车服务器、订单服务器]=>数据库
30.1.创建子项目，c2c-system-web_war
30.1.1.parent项目-右键New Module-maven-skip archetype
30.1.2.在pom.xml中添加：
<packaging>war</packaging>
添加插件spring-boot-maven-plugin
引入c2c-system-common依赖
30.1.3.生成"src\main\webapp\WEB-INF\pages\"文件夹
30.1.4.添加主启动类和application.yml配置文件
30.1.5.application.yml去掉数据库相关配置
30.1.6.启动类上加上，@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
//common项目中包含数据库驱动包，加载包需要配置数据源
//表示启动时不需要数据源，不需要配置yml文件，spring.datasource
30.1.7.配置前台站点的，nginx、hosts配置
hosts:
127.0.0.1       www.c2c-system.com
nginx:
# 前台站点配置
server{
    listen  80;#浏览器输入端口
    server_name  www.c2c-system.com;#浏览器输入域名

    location / { 
        proxy_pass http://localhost:8082/; #前端页面地址		
    }
    location /api/ {
        proxy_pass   http://192.168.0.105:8072/; #后台接口地址(家里)            
    }
}
30.1.8.搜索海量数据，倒排索引，根据关键字检索文章的位置
关键字=>索引表（key:,index:）=>数据库
数据库：
1.游戏软件
2.游戏硬件
3.游戏平台
索引表，分词
key: index:
游戏 {1,2,3}
软件 {1}
硬件 {2}
平台 {3}
拿到关键字，从索引表中先搜索，提高效率
30.1.9.伪静态
保证搜索引擎收录（只收录html） 和 开发效率（用动态页面编写）
springMVC拦截html结尾的请求
开启后缀匹配
配置类WebMvcConfigurer.configurePathMatch，WebMvcConfigurer类似web.xml
测试PageController.module
http://localhost:8072/test和http://localhost:8072/test.html都能访问
30.1.10.商品详情信息的展现
点击首页的商品，跳转商品详情页
system-web，只能直接引入自己子项目中的文件，要想调用background_system的ProductService获得product信息，
必须发起http请求
java代码发起http请求，获取json数据，HttpClient
第三方sdk:第三方定义的接口工具api，底层就是HttpClient
30.1.10.1.HttpClient技术
测试类，TestHttpClient，获取百度页面html代码

spring整合HttpClient
HttpClientConfig 配置类，实例化CloseableHttpClient,RequestConfig两个对象
httpClient.properties httpClient配置文件
HttpClientClose 封装连接关闭
HttpClient工具api的封装 HttpClientService
HttpClient工具api测试类 TestHttpClientUtil

在web服务器中的ProductServiceImpl中，通过httpClient调用backgroundSystem服务器，再把返回的json转化成对象
先写backgroundSystem中的Controller,Service,Mapper
controller在com.qxn.controller.web中重写，便于区分
Service,Mapper沿用backgroundSystem的就行

web服务器中ProductServiceImpl调用httpClient获得json
ObjectMapperUtil.toObject();转对象返回
productDesc也是一样获取，浏览器请求拿到数据，展现商品详情页

自己在web子项目中写一个findProducts的接口，调backgroundSystem的findProductByPage接口，
获取商品信息，在首页推荐展现用

好处：只有backgroundSystem连接数据库，如果数据库表变化，只需要改backgroundSystem中代码即可，实现解耦

写c2c-system_web_frontEnd中，首页和商品详情页

搜索引擎 IK分词器

30.2.jsonp跨域实现
后台接口 WebJsonpController.testJsonp, WebJsonpController.testJsonpObject
31.sso单点登录系统，用户只需登录一次，即可访问其它相互信任的系统

注册页面，用户信息校验
用户名注册输入框，失去焦点，查询数据库，是否重名，true重名，false不重

创建子jar包项目，c2c-system-sso，单点登录服务器
引入common子项目依赖，common中引用了redis.properties文件，添加redis.properties
添加application.yml配置文件

添加hosts、nginx.conf文件

添加主启动类，C2cSystemSsoApplication
配置run configurations中C2cSystemSsoApplication启动类

在common中添加pojo，User

写User业务相关的，mapper,service,controller

用jsonp实现校验用户信息，UserController.checkUser

31.1.dubbo框架
浏览器 => web.controller => 接口.service => sso.serviceImpl => sso.mapper
soa思想：服务消费者（web） => 接口 => 服务生产者（sso）
接口采用中立方式定义
http是应用层（7层）协议得用controller接收

osi模型：
物理层，2进制数据在物理媒体上传输数据
数据链路层，有地址的帧，错误检测。将要传输的数据，拆分成小包（帧），游戏中卡顿就是掉帧
网络层，为数据包选择路由。发往的地址
传输层，提供端对端的接口。tcp/udp。tcp3次握手，在？在，好。udp直接请求，不管对方状态，可能失败
会话层，解除或建立与其它接点的联系。
表示层，数据格式化，代码转换，数据解密。数据加密/解密
应用层，文件传输，电子邮件，文件服务，虚拟终端。http/ftp

系统A（应用层=>物理层）=>系统A（物理层=>应用层）

rpc是传输层（4层）协议，rpc可以直接调用中立接口（代码） 
rpc是长连接，一个连接上可以连续发送多个数据包，连接保持期间，没有数据包发送，需要双方发送链路检测包
rpc加密压缩传输，http协议大量请求头信息
rpc协议有注册中心，监控机制

http协议传对象，rpc协议传对象，对象得实现序列化接口

dubbo介绍，高性能服务框架（soa），通过rpc实现服务的输入输出
web服务器（消费者）=> 通过rpc协议连接多个订单服务器，也需要负载均衡，需要额外的注册中心 => [order服务器（生产者）,order服务器]
注册中心，记录服务的列表
生成者：ip地址，端口，服务名称，启动时会将信息写到注册中心服务列表中
注册中心把服务列表写到消费者中，
浏览器访问web服务器时，消费者会在列表中选择一个生产者访问，实现负载均衡
如果某一个生产者宕机了，
注册中心通过心跳检测机制，检测到了，将列表状态更新，让消费者更新数据
正向代理

生产者启动，将ip、端口号、服务名称写入注册中心
注册中心接收服务器请求，将信息写入服务列表
消费者启动，连接注册中心，获取服务列表，保存到本地内存中
用户发起请求，消费者查询列表，基于负载均衡选择ip、端口，发起rpc请求
生产者处理请求，返回数据
注册中心基于心跳检测机制，发现生产者宕机，更新服务列表，并全网广播

步骤：
31.1.1.zookeeper注册中心
zookeeper，自动化服务调度器

在linux安装java.jdk
cd /usr/local/src/
上传zookeeper安装文件
拖入zookeeper-3.4.14.tar.gz
解压tar -xvf zookeeper-3.4.14.tar.gz
rm -rf zookeeper-3.4.14.tar.gz
改名mv zookeeper-3.4.14 zookeeper
创建zookeeper信息和日志文件夹
cd zookeeper/
mkdir data log
复制配置文件并改名
cd conf/
cp zoo_sample.cfg zoo.cfg
vim zoo.cfg
配置数据、日志文件夹
dataDir=/usr/local/src/zookeeper/data
dataLogDir=/usr/local/src/zookeeper/log

cd ../bin/
启动sh zkServer.sh start
查看状态 sh zkServer.sh status
关闭sh zkServer.sh stop

搭建zookeeper集群，3台
zookeeper功能一样，redis有扩容作用
cd ..
mkdir zkCluster
cd zkCluster/
mkdir zk1 zk2 zk3
在zk1 zk2 zk3中创建data,log文件夹
mkdir {zk1,zk2,zk3}/{data,log}
标识文件myid
vim zk1/data/myid，输入1
vim zk2/data/myid，输入2
vim zk3/data/myid，输入3

cd ../../../conf/
准备3个配置文件
cp zoo.cfg zoo1.cfg
vim zoo1.cfg

dataDir=/usr/local/src/zookeeper/zkCluster/zk1/data
dataLogDir=/usr/local/src/zookeeper/zkCluster/zk1/log
# the port at which the clients will connect
clientPort=2181
ip:通信端口:选举端口
server.1=192.168.89.129:2887:3887
server.2=192.168.89.129:2888:3888
server.3=192.168.89.129:2889:3889

复制改zoo2.cfg,zoo3.cfg

cd ../bin/
sh zkServer.sh start zoo1.cfg
sh zkServer.sh start zoo2.cfg
sh zkServer.sh start zoo3.cfg
查看状态
sh zkServer.sh status zoo3.cfg
主机leader，从机follower

停止
sh zkServer.sh stop zoo1.cfg
查看错误日志
cat zookeeper.out

31.1.2.dubbo使用方法：
31.1.2.1.定义接口
31.1.2.2.服务提供者，引入接口子项目
没有controller
引入dubbo的@Service
application.yml添加dubbo配置：包扫描，定义服务名，设置注册中心，rpc调用端口
31.1.2.3.服务消费者
主启动类去数据库
@Reference注解注入service
application.yml：定义服务名，连接zookeeper

dubbo负载均衡策略
随机（默认）：
@Reference(loadbalance="random")
轮询：
@Reference(loadbalance="roundrobin")
一致性hash：
相同参数的请求发到同一个提供者上，该提供者宕机时，它的请求平摊到其他提供者
@Reference(loadbalance="consistenthash")
最少使用：
选择上次响应时长最短服务器
@Reference(loadbalance="leastactive")

zookeeper宕机时，现有服务器仍能工作，因为列表在消费者内存中

zookeeper，树形结构保存信息

31.1.3.c2c-system中加入dubbo
接口写在common.DubboUserService

sso是生产者
引入dubbo依赖
配置sso配置文件，增加dubbo配置
增加实现类DubboUserServiceImpl

web是消费者
修改配置文件

31.1.4.dubbo控制台
dubbo服务监控，包括服务者、消费者
是一个tomcat服务器，在阿里下载，
将tomcat-dubbo\webapps\ROOT\WEB-INF\dubbo.properties中的ip更改
启动tomcat-dubbo\bin\startup.sh，访问http://localhost:8090/
设置权重，权重越大，访问越多

31.1.5.用户信息注册
密码加密
String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
user.setPassword(md5Pass);

31.1.6.单点登录实现
登录一次，可访问其它信任的系统，sso
在web服务器集群中任一台登录后，可直接登录其它web服务器，或者直接登录backgroundSystem服务器

流程：
第一次登录：
浏览器=>nginx=>前台服务器=>sso单点登录服务器=>数据库=>sso单点登录服务器，user的json对象和全球唯一的ticket=>
[
存入redis服务器(k:v。key唯一，ticket，也叫token，user对象json)

sso同时返回ticket给web=>nginx=>浏览器，web将ticket交给用户的浏览器cookie中，设置过期时间，设置数据共享？
]

后面登录：
浏览器中的ticket=>nginx=>前台服务器=>用ticket查询redis服务器，
没值，跳转到登录页面？
有值，直接判断登录成功

其它信任系统需要，cookie数据共享

设置cookie生命周期
new Cookie("key", value)
    .setMaxAge(>0);存活的生命周期
    .setMaxAge(0);立即删除
    .setMaxAge(-1);会话关闭时，删除cookie;

DubboUserServiceImpl：
cookie共享
//cookie的权限设定（路径）根目录下
new Cookie("key", value).setPath("/");
//cookie实现共享，不写www，二级域名可用
new Cookie("key", value).setDomain("c2c-system.com");

ticket是uuid生成后再md5加密
String uuid = UUID.randomUUID().toString();
String ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());

敏感数据脱敏，不用返回给用户
userDB.setPassword("***");	
sso服务器查到数据，存入redis服务器
jedisCluster.setex(ticket, 7*24*3600, userJSON);

登录成功后，用户信息在首页的展现
jsonp访问sso接口，查询redis服务器中是否含有对应数据，返回
sso中UserController.queryByCookie1

用户登出
删除浏览器cookie信息，redis记录，前端重定向到首页
web.UserController.logout1

31.基于dubbo，新建c2c-system-cart，购物车服务器

创建子jar包项目，c2c-system-cart
引入common子项目依赖，common中引用了redis.properties文件，添加redis.properties
添加application.yml配置文件，改端口

添加hosts文件
nginx.conf不需要加了，因为web服务器通过dubbo访问cart

添加主启动类，C2cSystemCartApplication
配置run configurations中C2cSystemCartApplication启动类

在common中添加pojo/Cart，接口DubboCartService
cart中写mapper,xml,serviceImpl，修改application.yml中dubbo服务名和端口
web中写controller，CartController

商品，类型、尺寸分别不同id
购物车表记录，用户id和商品id是联合主键，两个id确定一个记录

更新购物车商品数量
CartController.updateCartNum
修改一条购物记录，需要两个ak，itemId和userId
前端传入itemId、num，后台获取userId，注入Cart对象，传入service更改

common.pojo.Product
//爬虫中使用最多
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时，忽略未知属性（images）

新增购物车商品
DubboCartServiceImpl.addItem
根据item_id和user_id查询表，无记录则新增记录，有记录则更新记录的num和updated

31.1.权限控制，不登录不能看购物车、订单等个人页面
拦截器：
条件：客户端是否有cookie，redis中是否有记录
web.xml => 拦截器类，拦截器配置文件
由于只对web站点生效，加在web项目中

拦截器类：
web.UserInterceptor implements HandlerInterceptor

HandlerInterceptor：
preHandle controller前
postHandle service业务方法后
afterCompletion 视图渲染后

拦截器配置文件：
web.MvcConfigurer implements WebMvcConfigurer
MvcConfigurer.addInterceptors

cookie工具类
web.CookieUtil

31.2.动态获取用户id
将UserInterceptor.preHandle拦截器中查到的redis.userJson，存储起来，给web.CartController中的方法提供userId使用
31.2.1.存入request域中
CartController中，
(User)request.getAttribute(C2CSYSTEM_USER);取对象
31.2.2.存入ThreadLocal线程池中（推荐）
ThreadLocal在线程内实现数据共享
在web中写ThreadLocalUtil工具类

CartController中，
User user = ThreadLocalUtil.get();取对象

31.2.3.页面渲染完后销毁域中数据，结束ThreadLocalUtil线程
UserInterceptor.afterCompletion
request.removeAttribute(C2CSYSTEM_USER);
ThreadLocalUtil.remove();

32.基于dubbo，新建c2c-system-order，订单服务器

创建子jar包项目，c2c-system-order
引入common子项目依赖，common中引用了redis.properties文件，添加redis.properties
添加application.yml配置文件，改端口

添加hosts文件
nginx.conf不需要加了，因为web服务器通过dubbo访问cart

添加主启动类，C2cSystemOrderApplication
配置run configurations中C2cSystemOrderApplication启动类

web中写OrderController
在common中添加pojo/Order,OrderItem订单商品信息,OrderShipping订单物流信息，接口DubboOrderService
order中写mapper,xml,serviceImpl，修改application.yml中dubbo服务名和端口

32.1.提交订单

生成orderId
不用uuid，有字母不便用户输入和订单查找
用userId+时间戳

Order
@TableField(exist=false)	//入库操作忽略该字段
private OrderShipping orderShipping;

Order,OrderItem订单商品信息,OrderShipping订单物流信息
OrderId唯一，3张表入库，事务控制

32.2.订单查询
根据OrderId查询数据库，3表查询，然后展现数据
32.2.1.mybatisPlus方式
DubboOrderServiceImpl.queryOrderByIdMybatisPlus
32.2.2.手写sql方式
DubboOrderServiceImpl.queryOrderByIdSql
OrderMapper.xml中resultMap用法

32.3.订单超时处理，Quartz定时任务
规定时间未支付，订单设置为关闭，status=6
策略：
定期开启一个线程，执行超时任务，1分钟跑一次
条件：now() - created > 30分钟 && status = 1
Quartz
调度原理：
Scheduler调度器，统一管理任务
Trigger触发器，执行任务，设定执行周期
Job，自定义任务
JobDetail，封装Job的api

创建Scheduler，Job注册到Scheduler，Scheduler调度Trigger，Trigger创建一个线程，执行Job的任务

步骤：
32.3.1.导包

32.3.2.conf.OrderQuartzConfig配置类：
orderjobDetail封装job任务

Quartz表达式生成器，设置周期或者自定义

trigger绑定到调度器上

32.3.3.quartz.OrderQuartz extends QuartzJobBean
executeInternal，所要执行的任务

33.优化单点登录
33.1.防止盗取cookie免密登录
添加ip校验，用户登录时，获取用户ip，
用redis的hash存储，Username:{C2CSYSTEM_TICKET:ticket,"C2CSYSTEM_USERJSON":userJson,"C2CSYSTEM_IP":用户ip}
将Username，ticket保存在用户cookie中
用户登录时，校验ip和ticket

步骤：
33.2.web.UserController.doLogin2
用了nginx，需要用工具类获取真实ip，common添加IPUtil工具类

需要告知nginx携带真实ip，nginx.conf添加配置，在sso,web,manage的proxy_pass后加上
# 真实ip地址
proxy_set_header X-Real-IP $remote_addr;
# 真实ip端口
proxy_set_header X-Real-Port $remote_port;
# 代理转发代理时
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

将登录的user，真实ip传入service，从service接收ticket

在web.CookieUtil中加入写cookie方法，addCookie

将ticket,username写入用户浏览器cookie

33.3.DubboUserServiceImpl.doLogin2
将Username集合写入redis
Username:{C2CSYSTEM_TICKET:ticket,"C2CSYSTEM_USERJSON":userJson,"C2CSYSTEM_IP":用户ip}
ticket:uuid的md5加密
userJson:根据controller的参数，从数据库查出的userJson字符串
ip:controller的参数
返回ticket

34.单点登录回显
根据用户传入cookie中username，获取redis中username对象{ip,ticket,userJson}
校验ip地址，获取当前用户ip地址，和redis中ip地址比对，如果不一致，要重新登录，防止盗用cookie登录
校验ticket信息，传入ticket与redis中ticket比对，比对失败，要重新登录，说明你后来在其它浏览器再次登录了
ip和ticket校验通过，返回userJson

35.退出登录重写
UserController.logout2
利用CookieUtil，取cookie中USERNAME值，
在redis中删除username的集合，结束浏览器TICKET，USERNAME的cookie生命周期

36.quick start，重新导入项目后
36.1.配置nginx.conf和hosts，启动nginx
36.2.打开VMware，运行Xshell，linux安装运行redis cluster，zookeeper
36.3.运行前端项目c2c-system_backgroundSystem_frontEnd，c2c-system_web_frontEnd
36.4.导入数据库c2csystemdb.sql
36.5.导入c2c-system后台聚合项目，运行

```
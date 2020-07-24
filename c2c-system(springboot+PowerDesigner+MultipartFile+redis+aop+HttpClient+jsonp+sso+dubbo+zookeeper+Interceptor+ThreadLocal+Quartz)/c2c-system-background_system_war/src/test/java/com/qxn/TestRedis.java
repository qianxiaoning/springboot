package com.qxn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
    private Jedis jedis;
    @Before
    public void init() {
        jedis = new Jedis("192.168.89.129",6379);
    }
    /**
     * 分布式锁：
     * redis创建一个锁
     * jedis.setnx
     * jedis.setex()给个失效时间
     */
    @Test
    public void testString() throws Exception {
        jedis.set("qxn","redis");
        String value = jedis.get("qxn");
        System.out.println(value);
        //2.测试key相同时value是否覆盖
        jedis.set("qxn","redis测试");
        System.out.println(jedis.get("qxn"));
        //3.如果值已经存在则不允许覆盖
        jedis.setnx("qxn", "qw");
        System.out.println(jedis.get("qxn"));
        //4.为数据添加超时时间
        jedis.set("time","超时测试");
        jedis.expire("time", 60);
        //保证数据操作有效性（原子性：同时成功或失败）
        jedis.setex("time", 100, "超时测试");
        Thread.sleep(3000);
        Long time = jedis.ttl("time");
        System.out.println("当前数据还能存活："+time+"秒");
        //5.要求key存在时不允许操作，并且设定超时时间
        //nx不许覆盖，xx可以覆盖
        //ex单位秒 px单位毫秒
        jedis.set("时间","测试是否有效","NX","EX",100);
        System.out.println(jedis.get("时间"));
    }
    /**
     * 2.测试hash
     */
    @Test
    public void testHash() {
        jedis.hset("user", "id", "1");
        jedis.hset("user", "name", "a");
        System.out.println(jedis.hgetAll("user"));
    }
    /**
     * 3.测试list
     * 队列
     */
    @Test
    public void testList() {
        jedis.del("list");
        jedis.rpush("list", "1,2,3,4");
        System.out.println(jedis.lpop("list"));//1,2,3,4
        jedis.rpush("list", "1","2","3","4");
        System.out.println(jedis.lpop("list"));//1
        jedis.rpush("list", "1","2","3","4");
        System.out.println(jedis.lpop("list"));//2
    }
    /**
     * 4.事务
     */
    @Test
    public void testTx() {
        //1.开启事务
        Transaction transaction = jedis.multi();
        try {
            transaction.set("a", "aaa");
            transaction.set("b", "bbb");
            transaction.set("c", "ccc");
            int a = 1/0;
            //2.事务提交
            transaction.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //3.事务回滚
            transaction.discard();
        }
    }
    /**
     * 二、redis分片操作
     */
    @Test
    public void testShards() {
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(new JedisShardInfo("192.168.89.129",6379));
        list.add(new JedisShardInfo("192.168.89.129",6380));
        list.add(new JedisShardInfo("192.168.89.129",6381));
        ShardedJedis jedis = new ShardedJedis(list);

        jedis.set("qxn", "redis分片测试");
        System.out.println(jedis.get("qxn"));
    }
    /**
     * 测试哨兵
     * 调用原理：
     * 用户通过哨兵连接redis的主机，进行操作
     * masterName：主机变量名称
     * sentinels:redis节点的信息 Set<String>
     */
    @Test
    public void testSentinel() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.89.129:26379");
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",sentinels);
        Jedis jedis = sentinelPool.getResource();
        jedis.set("qxn","哨兵测试成功");
        System.out.println(jedis.get("qxn"));
    }
    @Test
    public void testCluster() {
        Set<HostAndPort> node = new HashSet<HostAndPort>();
        node.add(new HostAndPort("192.168.89.129",7000));
        node.add(new HostAndPort("192.168.89.129",7001));
        node.add(new HostAndPort("192.168.89.129",7002));
        node.add(new HostAndPort("192.168.89.129",7003));
        node.add(new HostAndPort("192.168.89.129",7004));
        node.add(new HostAndPort("192.168.89.129",7005));
        JedisCluster jedisCluster = new JedisCluster(node);
        jedisCluster.set("qxn", "redis集群测试成功");
        System.out.println(jedisCluster.get("qxn"));
    }
}

package com.company.springbootquickstart01.springBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * 版权：(C) 版权所有 2000-2014 上海天好电子商务有限公司苏州分公司
 * <简述> Redis数据操作类
 * <详细描述>
 * @author   lilin
 * @version  $Id$
 * @since
 * @see
 */
@Repository
public class RedisOperate {

    /**
     * @Field redisTemplate ： redis连接模板工具
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     *〈简述〉设置value格式
     *〈详细描述〉对象、列表、集合
     * @author lilin
     */
    private void setValueSerializer() {
        setValueSerializer(Object.class);
    }

    /**
     *〈简述〉设置value格式
     *〈详细描述〉对象、列表、集合
     * @author lilin
     * @param <T> 泛型
     * @param clazz java类型
     */
    private <T> void setValueSerializer(Class<T> clazz) {
        JavaType javaType = constructType(clazz);
        setValueSerializer(javaType);
    }

    /**
     *〈简述〉设置value格式
     *〈详细描述〉对象、列表、集合
     * @author lilin
     * @param <T> 泛型
     * @param typeReference java类型
     */
    private <T> void setValueSerializer(TypeReference<T> typeReference) {
        setValueSerializer(TypeFactory.defaultInstance().constructType(typeReference));
    }

    /**
     *〈简述〉设置value格式
     *〈详细描述〉对象、列表、集合
     * @author lilin
     * @param <T> 泛型
     * @param javaType java类型
     */
    private <T> void setValueSerializer(JavaType javaType) {
        RedisSerializer<T> redisSerializer = redisSeralizer(javaType);
        redisTemplate.setValueSerializer(redisSerializer);
    }

    /**
     *〈简述〉设置map的key格式
     *〈详细描述〉
     * @author lilin
     */
    private void setHashKeySerializer() {
        setHashKeySerializer(Object.class);
    }

    /**
     *〈简述〉设置map的key格式
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param clazz java类型
     */
    private <T> void setHashKeySerializer(Class<T> clazz) {
        setHashKeySerializer(constructType(clazz));
    }

    /**
     *〈简述〉设置map的key格式
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param javaType java类型
     */
    private <T> void setHashKeySerializer(JavaType javaType) {
        RedisSerializer<T> redisSerializer = redisSeralizer(javaType);
        redisTemplate.setHashKeySerializer(redisSerializer);
    }

    /**
     *〈简述〉设置map的value格式
     *〈详细描述〉
     * @author lilin
     */
    private void setHashValueSerializer() {
        setHashValueSerializer(Object.class);
    }

    /**
     *〈简述〉设置map的value格式
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param clazz java类型
     */
    private <T> void setHashValueSerializer(Class<T> clazz) {
        setHashValueSerializer(constructType(clazz));
    }

    /**
     *〈简述〉设置map的value格式
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param typeReference java类型
     */
    private <T> void setHashValueSerializer(TypeReference<T> typeReference) {
        setHashValueSerializer(TypeFactory.defaultInstance().constructType(typeReference));
    }

    /**
     *〈简述〉设置map的value格式
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param javaType java类型
     */
    private <T> void setHashValueSerializer(JavaType javaType) {
        RedisSerializer<T> redisSerializer = redisSeralizer(javaType);
        redisTemplate.setHashValueSerializer(redisSerializer);
    }

    /**
     *〈简述〉创建redis序列化
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param clazz java类型
     * @return 序列化格式
     */
    private <T> JavaType constructType(Class<T> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }

    /**
     *〈简述〉创建redis序列化
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param javaType java类型
     * @return 序列化格式
     */
    private <T> RedisSerializer<T> redisSeralizer(JavaType javaType) {
        Jackson2JsonRedisSerializer<T> redisSerializer = new Jackson2JsonRedisSerializer<>(javaType);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        redisSerializer.setObjectMapper(objectMapper);
        return redisSerializer;
    }

    /**
     *〈简述〉创建redis序列化
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param clazz java类型
     * @return 序列化格式
     */
    private <T> RedisSerializer<T> redisSeralizer(Class<T> clazz) {
        return redisSeralizer(constructType(clazz));
    }

    /****************************key 操作开始*******************************/
    /**
     *〈简述〉删除key
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     *〈简述〉删除key集合
     *〈详细描述〉
     * @author lilin
     * @param keys key集合
     */
    public void del(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     *〈简述〉删除key集合
     *〈详细描述〉模糊查询key集合
     * @author lilin
     * @param pattern 模糊查询
     */
    public void delPattern(String pattern) {
        del(keys(pattern));
    }

    /**
     *〈简述〉key是否存在
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @return true：存在
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     *〈简述〉模糊查询key集合
     *〈详细描述〉
     * @author lilin
     * @param pattern 模糊查询
     * * 匹配数据库中所有 key
     * * 匹配任意字符 例如：h*llo 匹配 hllo 和 heeeeello 等
     * ？ 匹配单个字符 例如：h?llo 匹配 hello ， hallo 和 hxllo 等
     * [] 匹配范围
     * 例1：h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo
     * 例2：a[b-d] 匹配ab、ac、ad
     * 特殊符号需要转义字符 用\
     * @return key集合
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
    /****************************key 操作结束*******************************/

    /****************************string 操作开始*******************************/
    /**
     *〈简述〉获取字符串
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @return 字符串
     */
    public String get(String key) {
        setValueSerializer(String.class);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return (String) opsForValue.get(key);
    }

    /**
     *〈简述〉添加字符串
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param value 字符串
     */
    public void set(String key, String value) {
        setValueSerializer(String.class);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    /**
     *〈简述〉追加字符串
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param value 字符串
     * @return 字符串长度
     */
    public Integer append(String key, String value) {
        setValueSerializer(String.class);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.append(key, value);
    }

    /**
     *〈简述〉截取字符串
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param start 起始位置
     * @param end 结束位置
     * @return 字符串
     */
    public String getRange(String key, long start, long end) {
        setValueSerializer(String.class);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return opsForValue.get(key, start, end);
    }
    /****************************string 操作结束*******************************/

    /****************************object （对象）操作开始*******************************/
    /**
     *〈简述〉获取对象
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param clazz 对象类型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        setValueSerializer(clazz);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return (T) opsForValue.get(key);
    }

    /**
     *〈简述〉获取对象
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param typeReference java类型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, TypeReference<T> typeReference) {
        setValueSerializer(typeReference);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return (T) opsForValue.get(key);
    }

    /**
     *〈简述〉添加对象
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param value 对象值
     */
    public void set(String key, Object value) {
        setValueSerializer();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    /**
     *〈简述〉获取对象集合
     *〈详细描述〉根据key集合
     * @author lilin
     * @param <T> 泛型
     * @param keys redis的key集合
     * @param clazz 对象类型
     * @return 对象列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> mget(Collection<String> keys, Class<T> clazz) {
        setValueSerializer(clazz);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return (List<T>) opsForValue.multiGet(keys);
    }

    /**
     *〈简述〉获取对象集合
     *〈详细描述〉key模糊查询
     * @author lilin
     * @param <T> 泛型
     * @param pattern 模糊查询
     * @param clazz 对象类型
     * @return 对象列表
     */
    public <T> List<T> mget(String pattern, Class<T> clazz) {
        return mget(keys(pattern), clazz);
    }

    /**
     *〈简述〉添加对象集合
     *〈详细描述〉
     * @author lilin
     * @param map 对象map，key：redis的key， value 对象
     */
    public void mset(Map<String, Object> map) {
        setValueSerializer();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.multiSet(map);
    }
    /****************************object （对象）操作结束*******************************/

    /****************************hash （哈希表）操作开始*********************************/
    /**
     *〈简述〉删除map的key
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKeys map的key集合
     */
    public <K, V> void hdel(String key, Object... hashKeys) {
        setHashKeySerializer();
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        opsForHash.delete(key, hashKeys);
    }

    /**
     *〈简述〉获取map下的key的值
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKey map的key
     * @param clazzValue value类型
     * @return value对象
     */
    public <K, V> V hget(String key, K hashKey, Class<V> clazzValue) {
        setHashKeySerializer();
        setHashValueSerializer(clazzValue);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hashKey);
    }

    /**
     *〈简述〉获取map下的key的值
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKey map的key
     * @param typeReference value类型
     * @return value对象
     */
    public <K, V> V hget(String key, K hashKey, TypeReference<V> typeReference) {
        setHashKeySerializer();
        setHashValueSerializer(typeReference);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hashKey);
    }

    /**
     *〈简述〉获取对象
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <T> 对象泛型
     * @param key redis的key
     * @param clazz 对象类型
     * @return 对象
     */
    public <K, T> T hget(String key, Class<T> clazz) {
        final byte[] rawKey = new StringRedisSerializer().serialize(key);
        Map<byte[], byte[]> entries = redisTemplate.execute(new RedisCallback<Map<byte[], byte[]>>() {
            public Map<byte[], byte[]> doInRedis(RedisConnection connection) {
                return connection.hGetAll(rawKey);
            }
        }, true);
        return deserializeHashMap(entries, clazz);
    }

    /**
     *〈简述〉反序列化map为对象
     *〈详细描述〉
     * @author lilin
     * @param <T> 对象泛型
     * @param entries 序列化map
     * @param clazz 对象类型
     * @return 对象
     */
    private <T> T deserializeHashMap(Map<byte[], byte[]> entries, Class<T> clazz) {
        Map<String, byte[]> map = deserializeHashKey(entries);
        if (map.isEmpty()) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = redisSeralizer(property.getPropertyType()).deserialize(map.get(key));
                    Method setter = property.getWriteMethod();
                    setter.invoke(t, value);
                }
            }
        } catch (IntrospectionException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        }
        return t;
    }

    /**
     *〈简述〉反序列化键
     *〈详细描述〉
     * @author lilin
     * @param entries 序列化map
     * @return 反序列化键map
     */
    private Map<String, byte[]> deserializeHashKey(Map<byte[], byte[]> entries) {
        Map<String, byte[]> map = new HashMap<>();
        if (entries == null || entries.isEmpty()) {
            return map;
        }
        for (Entry<byte[], byte[]> entry : entries.entrySet()) {
            map.put(new StringRedisSerializer().deserialize(entry.getKey()), entry.getValue());
        }
        return map;
    }

    /**
     *〈简述〉添加一个键值对
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKey map的key
     * @param value map的value
     */
    public <K, V> void hset(String key, K hashKey, V value) {
        setHashKeySerializer();
        setHashValueSerializer();
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key, hashKey, value);
    }

    /**
     *〈简述〉将对象以键值对的形式存在redis中
     *〈详细描述〉redis类型为map
     * @author lilin
     * @param key redis的key
     * @param object 对象
     */
    public void hset(String key, Object object) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        setHashValueSerializer();
        Map<String, Object> map = beanToMap(object);
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.putAll(key, map);
    }

    /**
     *〈简述〉对象转成map键值对
     *〈详细描述〉
     * @author lilin
     * @param object 对象
     * @return map键值对
     */
    private static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
//        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
//        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(object);
//        for (PropertyDescriptor propertyDescriptor : descriptors) {
//            String name = propertyDescriptor.getName();
//            if ("class".equals(name) || "new".equals(name)) {
//                continue;
//            }
//            try {
//                map.put(name, propertyUtilsBean.getProperty(object, name));
//            } catch (IllegalAccessException | InvocationTargetException
//                | NoSuchMethodException e) {
//                LOGGER.error(e.getMessage(), e);
//            }
//        }
        return map;
    }

    /**
     *〈简述〉获取整个map
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param clazzKey key类型
     * @param clazzValue value类型
     * @return map
     */
    public <K, V> Map<K, V> hgetAll(String key, Class<K> clazzKey, Class<V> clazzValue) {
        setHashKeySerializer(clazzKey);
        setHashValueSerializer(clazzValue);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     *〈简述〉获取整个map
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param clazzValue value类型
     * @return map
     */
    public <K, V> Map<K, V> hgetAll(String key, Class<V> clazzValue) {
        setHashKeySerializer(String.class);
        setHashValueSerializer(clazzValue);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     *〈简述〉获取整个map
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param clazzKey key类型
     * @param typeReference value类型
     * @return map
     */
    public <K, V> Map<K, V> hgetAll(String key, Class<K> clazzKey, TypeReference<V> typeReference) {
        setHashKeySerializer(clazzKey);
        setHashValueSerializer(typeReference);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     *〈简述〉获取整个map
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param typeReference value类型
     * @return map
     */
    public <K, V> Map<K, V> hgetAll(String key, TypeReference<V> typeReference) {
        setHashKeySerializer(String.class);
        setHashValueSerializer(typeReference);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     *〈简述〉获取map的key集合
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param clazz key类型
     * @return map下key的集合
     */
    public <K, V> Set<K> hkeys(String key, Class<K> clazz) {
        setHashKeySerializer(clazz);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.keys(key);
    }

    /**
     *〈简述〉获取map的value的集合
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param clazz value类型
     * @return map下value的集合
     */
    public <K, V> List<V> hvals(String key, Class<V> clazz) {
        setHashValueSerializer(clazz);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.values(key);
    }

    /**
     *〈简述〉获取map的value的集合
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param typeReference value类型
     * @return map下value的集合
     */
    public <K, V> List<V> hvals(String key, TypeReference<V> typeReference) {
        setHashValueSerializer(typeReference);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.values(key);
    }

    /**
     *〈简述〉获取map的value的集合
     *〈详细描述〉根据key集合
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKeys map的key集合
     * @param clazzValue value类型
     * @return map下value的集合
     */
    public <K, V> List<V> hmget(String key, Collection<K> hashKeys, Class<V> clazzValue) {
        setHashKeySerializer();
        setHashValueSerializer(clazzValue);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.multiGet(key, hashKeys);
    }

    /**
     *〈简述〉获取map的value的集合
     *〈详细描述〉根据key集合
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param hashKeys map的key集合
     * @param typeReference value类型
     * @return map下value的集合
     */
    public <K, V> List<V> hmget(String key, Collection<K> hashKeys, TypeReference<V> typeReference) {
        setHashKeySerializer();
        setHashValueSerializer(typeReference);
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        return opsForHash.multiGet(key, hashKeys);
    }

    /**
     *〈简述〉获取对象列表
     *〈详细描述〉key模糊查询
     * @author lilin
     * @param <T> 对象泛型
     * @param pattern redis的key模糊查询
     * @param clazz 对象类型
     * @return 对象列表
     */
    public <T> List<T> hmget(String pattern, Class<T> clazz) {
        Set<String> keys = keys(pattern);
        List<String> keysList = new ArrayList<>(keys);
        Collections.reverse(keysList);
        List<T> list = new ArrayList<>();
        for (String key : keysList) {
            list.add(hget(key, clazz));
        }
        return list;
    }

    /**
     *〈简述〉添加一组键值对
     *〈详细描述〉
     * @author lilin
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @param key redis的key
     * @param map 一组键值对
     */
    public <K, V> void hmset(String key, Map<K, V> map) {
        setHashKeySerializer();
        setHashValueSerializer();
        HashOperations<String, K, V> opsForHash = redisTemplate.opsForHash();
        opsForHash.putAll(key, map);
    }
    /****************************hash （哈希表）操作结束*********************************/

    /****************************list （列表）操作开始***********************************/
    /**
     *〈简述〉获取列表长度
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @return 列表大小
     */
    public Long llen(String key) {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.size(key);
    }

    /**
     *〈简述〉列表添加一个对象
     *〈详细描述〉在列表的最开始添加
     * @author lilin
     * @param key redis的key
     * @param value 对象
     * @return 列表长度
     */
    public Long lpush(String key, Object value) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.leftPush(key, value);
    }

    /**
     *〈简述〉列表添加一组对象
     *〈详细描述〉在列表的最开始添加
     * @author lilin
     * @param key redis的key
     * @param values 一组对象
     * @return 列表长度
     */
    public Long lpushAll(String key, Object... values) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.leftPushAll(key, values);
    }

    /**
     *〈简述〉列表添加一个对象
     *〈详细描述〉在列表的最后添加
     * @author lilin
     * @param key redis的key
     * @param value 对象
     * @return 列表长度
     */
    public Long rpush(String key, Object value) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.rightPush(key, value);
    }

    /**
     *〈简述〉列表添加一组对象
     *〈详细描述〉在列表的最后添加
     * @author lilin
     * @param key redis的key
     * @param values 一组对象
     * @return 列表长度
     */
    public Long rpushAll(String key, Object... values) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.rightPushAll(key, values);
    }

    /**
     *〈简述〉获取列表
     *〈详细描述〉下标：正数表示从前往后，负数表示从后往前，正数第一个下标为0，第二个为1，倒数第一个下标为-1，第二个为-2，以此类推，如：获取整个列表可以写成【0, -1】
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param start 下标开始
     * @param end 下标结尾
     * @param clazz 对象类型
     * @return 列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> lrange(String key, long start, long end, Class<T> clazz) {
        setValueSerializer(clazz);
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return (List<T>) opsForList.range(key, start, end);
    }

    /**
     *〈简述〉获取列表
     *〈详细描述〉下标：正数表示从前往后，负数表示从后往前，正数第一个下标为0，第二个为1，倒数第一个下标为-1，第二个为-2，以此类推，如：获取整个列表可以写成【0, -1】
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param start 下标开始
     * @param end 下标结尾
     * @param typeReference 对象类型
     * @return 列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> lrange(String key, long start, long end, TypeReference<T> typeReference) {
        setValueSerializer(typeReference);
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return (List<T>) opsForList.range(key, start, end);
    }

    /**
     *〈简述〉获取列表
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param clazz 对象类型
     * @return 列表
     */
    public <T> List<T> lrange(String key, Class<T> clazz) {
        return lrange(key, 0L, -1L, clazz);
    }

    /**
     *〈简述〉获取列表
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param typeReference 对象类型
     * @return 列表
     */
    public <T> List<T> lrange(String key, TypeReference<T> typeReference) {
        return lrange(key, 0L, -1L, typeReference);
    }

    /**
     *〈简述〉移除列表中的对象
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param count 移除个数
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     * @param value 对象值
     * @return 移除对象个数
     */
    public Long lrem(String key, long count, Object value) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.remove(key, count, value);
    }

    /**
     *〈简述〉将列表下标为 index 的元素的值设置为 value
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param index 列表下标
     * @param value 对象
     */
    public void lset(String key, long index, Object value) {
        setValueSerializer();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        opsForList.set(key, index, value);
    }
    /****************************list （列表）操作结束***********************************/

    /****************************set （集合）操作开始************************************/
    /**
     *〈简述〉集合添加元素
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param values 对象数组
     * @return 集合长度
     */
    public Long sadd(String key, Object... values) {
        setValueSerializer();
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
        return opsForSet.add(key, values);
    }

    /**
     *〈简述〉获取集合大小
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @return 集合大小
     */
    public Long scard(String key) {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
        return opsForSet.size(key);
    }

    /**
     *〈简述〉移除集合元素
     *〈详细描述〉
     * @author lilin
     * @param key redis的key
     * @param values 对象数组
     * @return 移除对象个数
     */
    public Long srem(String key, Object... values) {
        setValueSerializer();
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
        return opsForSet.remove(key, values);
    }

    /**
     *〈简述〉获取集合
     *〈详细描述〉
     * @author lilin
     * @param <T> 泛型
     * @param key redis的key
     * @param clazz 对象类型
     * @return 集合
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> smembers(String key, Class<T> clazz) {
        setValueSerializer(clazz);
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
        return (Set<T>) opsForSet.members(key);
    }
    /****************************set （集合）操作结束************************************/

    /**
     *〈简述〉添加字符串
     *〈详细描述〉附加过期时间
     * @author lilin
     * @param key redis的key
     * @param value 字符串
     * @param timeout 过期时间
     * @param unit 时间格式
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        setValueSerializer(String.class);
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value, timeout, unit);
    }

}

package com.sly.medicineshop.common.redis.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 帮助类型
 *
 * @author SLY
 * @date 2020/10/7
 */
@Component
public class RedisHelper {
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存String值
     *
     * @param key   键
     * @param value 值
     */
    public void putStringValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存String值
     *
     * @param key     键
     * @param value   值
     * @param timeOut 超时时间
     */
    public void putStringValue(String key, String value, long timeOut) {
        stringRedisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 存String值
     *
     * @param key      键
     * @param value    值
     * @param timeOut  超时时间
     * @param timeUnit 时间单位
     */
    public void putStringValue(String key, String value, long timeOut, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * 获取String值
     *
     * @param key 键
     * @return java.lang.String
     */
    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置String值超时时间
     *
     * @param key     键
     * @param timeOut 超时时间
     */
    public void expireStringValue(String key, long timeOut) {
        stringRedisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置String值超时时间
     *
     * @param key      键
     * @param timeOut  超时时间
     * @param timeUnit 时间单位
     */
    public void expireStringValue(String key, long timeOut, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 删除String值
     *
     * @param key 键
     */
    public void deleteStringValue(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 存值
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存值
     *
     * @param key     键
     * @param value   值
     * @param timeOut 超时时间
     */
    public void put(String key, String value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 存值
     *
     * @param key      键
     * @param value    值
     * @param timeOut  超时时间
     * @param timeUnit 时间单位
     */
    public void put(String key, String value, long timeOut, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * 取值
     *
     * @param key 键
     * @return java.lang.Object
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 取值
     *
     * @param key   键
     * @param clazz 对象类型
     * @return java.lang.Class
     */
    public <T> T get(String key, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(redisTemplate.opsForValue().get(key), clazz);
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param timeOut 超时时间
     */
    public void expire(String key, long timeOut) {
        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置超时时间
     *
     * @param key      键
     * @param timeOut  超时时间
     * @param timeUnit 时间单位
     */
    public void expire(String key, long timeOut, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 删除值
     *
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

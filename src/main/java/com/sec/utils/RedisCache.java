package com.sec.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * description : redis缓存
 *
 * @author kunlunrepo
 * date :  2023-08-14 12:00
 */
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    // 设置缓存
    public <T> void setCacheObject(final String key, final T value){
        redisTemplate.opsForValue().set(key, value);
    }

    // 设置缓存
    public <T> void setCacheObject(final String key, final T value,
           final Integer timeout, final TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    // 设置有效时间
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    // 设置有效时间
    public boolean expire(final String key, final long timeout, TimeUnit timeUnit) {
        return expire(key, timeout, timeUnit);
    }

    // 获取缓存
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    // 删除单个对象
    public boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    // 删除集合对象
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    // 设置缓存集合
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPush(key, dataList);
        return count == null ? 0 : count;
    }

    // 获取缓存集合
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0 ,-1);
    }

    // 设置缓存集合
    public <T> long setCacheSet(final String key, final Set<T> dataList) {
        Long count = redisTemplate.opsForSet().add(key, dataList);
        return count == null ? 0 : count;
    }

    // 获取缓存集合
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // 设置缓存map
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    // 获取缓存map
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // 向map中存入数据
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    // 获取map中的数据
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    // 获取多个map的数据
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    // 获取缓存的基本对象列表
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

}

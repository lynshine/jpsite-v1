package com.mty.jpsite.configuration.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * redis 和一级缓存
 * Created by jiangpeng on 2018/11/19.
 */
public class RedisAndLocalCache implements Cache {
    /**
     * 本地一级缓存使用ConcurrentHashMap
     */
    ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();
    RedisCache redisCache;
    TwoLevelCacheManager cacheManager;

    public RedisAndLocalCache(TwoLevelCacheManager cacheManager, RedisCache redisCache) {
        this.redisCache = redisCache;
        this.cacheManager = cacheManager;
    }

    @Override
    public String getName() {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper wrapper = (ValueWrapper) local.get(key);
        /**一级缓存中没有值*/
        if (wrapper != null) {
            return wrapper;
        } else {
            /** 二级缓存中取*/
            wrapper = redisCache.get(key);
            if (wrapper != null) {
                local.put(key, wrapper);
            }

            return wrapper;
        }

    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return redisCache.get(key, type);
    }

    public <T> T get(Object o, Callable<T> callable) {
        return redisCache.get(o, callable);
    }


    @Override
    public void put(Object key, Object value) {
        System.out.println(value.getClass().getClassLoader());
        redisCache.put(key, value);
        clearOtherJVM();

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper v = redisCache.putIfAbsent(key, value);
        clearOtherJVM();
        return v;

    }

    @Override
    public void evict(Object key) {
        redisCache.evict(key);
        clearOtherJVM();

    }

    @Override
    public void clear() {
        redisCache.clear();

    }

    public void clearLocal() {
        this.local.clear();
    }

    protected void clearOtherJVM() {
        String topicName = "";
        cacheManager.publishMessage(topicName, redisCache.getName());
    }
}

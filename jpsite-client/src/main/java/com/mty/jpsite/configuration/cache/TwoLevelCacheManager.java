package com.mty.jpsite.configuration.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 二级缓存管理类
 * Created by jiangpeng on 2018/11/19.
 */
public class TwoLevelCacheManager extends RedisCacheManager {
    RedisTemplate redisTemplate;

    /**
     * @param cacheWriter
     * @param defaultCacheConfiguration
     * @param redisTemplate
     */
    public TwoLevelCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, RedisTemplate redisTemplate) {
        super(cacheWriter, defaultCacheConfiguration);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 使用RedisAndLocalCache代替spring boot自帶的RedisCache
     */
    @Override
    protected Cache decorateCache(Cache cache) {
        return new RedisAndLocalCache(this, (RedisCache) cache);
    }

    /**
     * 通知其他redis分布式节点，缓存改变
     *
     * @param topicName 队列名
     * @param cacheName 缓存名
     */
    public void publishMessage(String topicName, String cacheName) {
        this.redisTemplate.convertAndSend(topicName, cacheName);
    }

    /**
     * 清空本地一级缓存
     * 为了简单起见，一级缓存的同步更新仅仅是清空一级缓存，
     * 并非采用同步更新缓存项。一级缓存将在下一次get方法调用时再次从Redis中加载最新数据。
     *
     * @param name 缓存key name
     */
    public void receiver(String name) {
        RedisAndLocalCache cache = (RedisAndLocalCache) this.getCache(name);
        if (cache != null) {
            cache.clearLocal();
        }
    }
}

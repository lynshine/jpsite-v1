package com.mty.jpsite.configuration.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.UnsupportedEncodingException;

/**
 * 支持一二级缓存，使得性能到达极致,
 * Created by jiangpeng on 2018/11/19.
 */
@Conditional(CacheConfig.RedisCondtion.class)   // 通过RedisCondtion判断是否实例化bean
@Configuration
public class CacheConfig {
    // 定义一个redis 的频道，默认cache，用于pub/sub
    @Value("${springext.cache.redis.topic}")
    String topicName;

    @Bean
    public TwoLevelCacheManager cacheManager(StringRedisTemplate redisTemplate) {
        /**RedisCache需要一个RedisCacheWriter来实现读写Redis*/
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        /**SerializationPair用于Java和Redis之间的序列化和反序列化，这里使用JdkSerializationRedisSerializer，并在反序列化过程中，使用当前的ClassLoader*/
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        /**构造一个RedisCache的自定义，比如是否使用前缀，比如Key和Value的序列化机制*/
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        /**创建CacheManager，并返回给Spring容器*/
        TwoLevelCacheManager cacheManager = new TwoLevelCacheManager(writer, config, redisTemplate);
        return cacheManager;
    }

    /**
     * 监听器
     */
    @Bean
    MessageListenerAdapter listenerAdapter(final TwoLevelCacheManager cacheManager) {
        return new MessageListenerAdapter(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                byte[] bs = message.getChannel();
                try {
                    // Sub 一个消息，通知缓存管理器
                    String type = new String(bs, "UTF-8");
                    String cacheName = new String(message.getBody(), "UTF-8");
                    cacheManager.receiver(cacheName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Redis message
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topicName));
        return container;
    }

    static class RedisCondtion implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.redis.enable");
            return "true".equals(value);
        }
    }
}

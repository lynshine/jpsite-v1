package com.mty.jpsite.configuration.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Conditional(CacheConfig.RedisCondtion.class)   // 通过RedisCondtion判断是否实例化bean
@Configuration
public class RedisConfig {
    @Bean(name = "strKeyRedisTemplate")
    public RedisTemplate<Object, Object> strKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        return template;
    }

    @Bean(name = "jsonRedisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    /**
     * MessageListenerAdapter主要用来对消息进行序列化工作
     *
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MyRedisChannelListener());
        //改成使用 JDK序列化机制
        // adapter.setSerializer(new JdkSerializationRedisSerializer());
        return adapter;
    }

    /**
     * RedisMessageListenerContainer 的主要作用是在 Redis 客户端接收到消息后，
     * 通过 PatternTopic 派发消息到对应的消息监昕者，并构造一个线程池任务来调用 MessageListener
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("news.*"));
        return container;
    }
}

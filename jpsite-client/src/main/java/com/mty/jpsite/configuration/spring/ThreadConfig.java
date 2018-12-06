package com.mty.jpsite.configuration.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 开启异步线程配置
 */
@Configuration
@ComponentScan("com.mty.jpsite.util")  //该包下的类才可以使用
@EnableAsync  // 启用异步任务
public class ThreadConfig {
    // 执行需要依赖线程池，这里就来配置最多10个的线程池
    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }
}

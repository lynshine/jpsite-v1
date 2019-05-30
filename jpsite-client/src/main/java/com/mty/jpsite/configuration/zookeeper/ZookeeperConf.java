package com.mty.jpsite.configuration.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Zookeeper配置类信息
 */
@Conditional(ZookeeperConf.ZookeeperCondtion.class)   // 通过ZookeeperCondtion判断是否实例化bean
@Configuration
public class ZookeeperConf {
    private Logger logger = LoggerFactory.getLogger(ZookeeperConf.class);
    @Value("${zk.url}")
    private String zkUrl;

    @Bean
    public CuratorFramework curatorFramework() {
        /**重连策略*/
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        /**领导选举*/
        LeaderSelectorListenerAdapter listenerAdapter = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                // 领导节点，方法结束后退出领导。zk会再次重新选择领导
            }
        };
        LeaderSelector selector = new LeaderSelector(client, "/schedule", listenerAdapter);
        selector.autoRequeue();
        selector.start();
        /**节点变化通知curator*/
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                CuratorEventType type = curatorEvent.getType();
                if (type == CuratorEventType.WATCHED) {
                    WatchedEvent watchedEvent = curatorEvent.getWatchedEvent();
                    Watcher.Event.EventType eventType = watchedEvent.getType();
                    logger.info("====>节点变化通知 eventType: {} : path: {}", eventType, watchedEvent.getPath());
                    if (watchedEvent.getPath() != null) {
                        client.checkExists().watched().forPath(watchedEvent.getPath());
                    }
                }
            }
        });

        return client;
    }

    static class ZookeeperCondtion implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.zookeeper.enable");
            return "true".equals(value);
        }
    }
}
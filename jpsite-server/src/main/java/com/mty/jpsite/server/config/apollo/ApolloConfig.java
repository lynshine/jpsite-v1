package com.mty.jpsite.server.config.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * Apollo简单配置实现
 */
@Conditional(ApolloConfig.ApolloCondtion.class)   // 通过ApolloCondtion判断是否实例化bean
@Component
public class ApolloConfig {
    private Logger logger = LoggerFactory.getLogger(ApolloConfig.class);

    @Bean
    public ApolloConfig apolloConfig() {
        Config config = ConfigService.getAppConfig();       // config实例对于每个名称空间都是单例的，并且从不为null
        /**
         * 监听配置变化事件
         * 监听配置变化事件只在应用真的关心配置变化，需要在配置变化时得到通知时使用，比如：数据库连接串变化后需要重建连接等。
         */
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                logger.info("====>Apollo Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    logger.info("====>Apollo Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
                }
            }
        });
        ApolloConfig apolloConfig = new ApolloConfig();
        return apolloConfig;
    }

    /**
     * 内部类，自定义Condtion，需要实现Condition接口
     */
    static class ApolloCondtion implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.apollo.enable");
            return value.equals("true");
        }
    }
}

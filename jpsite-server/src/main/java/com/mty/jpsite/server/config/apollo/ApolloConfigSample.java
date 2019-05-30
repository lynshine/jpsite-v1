package com.mty.jpsite.server.config.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * Apollo简单配置实现
 * Created by jiangpeng on 2018/11/5.
 */
@Conditional(ApolloConfigSample.ApolloCondtion.class)   // 通过ApolloCondtion判断是否实例化bean
@Component
public class ApolloConfigSample {
    private Logger logger = LoggerFactory.getLogger(ApolloConfigSample.class);
    /**
     * ApolloConfig用来自动注入Config对象
     */
    @ApolloConfig
    private Config apolloConfig;

    /**
     * ApolloConfigChangeListener用来自动注册ConfigChangeListener
     */
    @ApolloConfigChangeListener
    private void sampleOnChange(ConfigChangeEvent changeEvent) {
        changeEvent.changedKeys().forEach(key -> {
            ConfigChange change = changeEvent.getChange(key);
            logger.info("====>sampleOnChange Found ApolloConfigSample change - key: {}, oldValue: {}, newValue: {}, changeType: {}", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
        });
    }

    /**
     * 内部类，自定义Condtion，需要实现Condition接口
     */
    static class ApolloCondtion implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.apollo.enable");
            return "true".equals(value);
        }
    }
}

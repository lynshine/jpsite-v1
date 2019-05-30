package com.mty.jpsite.security.social.qq.conf;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 第三方登录自动注册
 */
@Conditional(DemoConnectionSignUp.AutoSignUp.class)   //是否自动注册
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //todo 根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }

    static class AutoSignUp implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.autoSignUp.enable");
            return "true".equals(value);
        }
    }
}

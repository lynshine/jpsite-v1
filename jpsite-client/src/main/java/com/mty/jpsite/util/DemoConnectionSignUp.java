package com.mty.jpsite.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Conditional(DemoConnectionSignUp.AutoSignUp.class)
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UserService userService;

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setName(connection.getDisplayName());
        user.setImageUrl(connection.getImageUrl());
        userService.save(user);
        QueryWrapper query = new QueryWrapper();
        query.eq("image_url", connection.getImageUrl());
        return userService.getOne(query).getId().toString();
    }

    /**
     * 判断
     */
    static class AutoSignUp implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.autoSignUp.enable");
            return value.equals("true");
        }
    }
}

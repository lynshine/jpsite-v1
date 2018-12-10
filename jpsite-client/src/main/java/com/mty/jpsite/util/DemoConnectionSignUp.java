package com.mty.jpsite.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.entity.userRole.UserRole;
import com.mty.jpsite.server.service.user.UserService;
import com.mty.jpsite.server.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方登录自动注册
 */
@Conditional(DemoConnectionSignUp.AutoSignUp.class)
@Component
@Transactional
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public String execute(Connection<?> connection) {
        initUser(connection);
        QueryWrapper query = new QueryWrapper();
        query.eq("open_id", connection.createData().getProviderUserId());
        Integer userId = userService.getOne(query).getId();
        initUserRole(userId);
        return userId.toString();
    }

    /**
     * 判断是否需要自动注册
     */
    static class AutoSignUp implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.autoSignUp.enable");
            return value.equals("true");
        }
    }

    public void initUser(Connection<?> connection) {
        User user = new User();
        user.setName(connection.getDisplayName());
        user.setPassword("a123456..");
        user.setImageUrl(connection.getImageUrl());
        user.setOpenId(connection.createData().getProviderUserId());
        userService.save(user);
    }

    public void initUserRole(Integer userId) {
        UserRole userRole = new UserRole();
        userRole.setUid(userId);
        userRole.setRoleId(1);
        userRoleService.save(userRole);
    }
}

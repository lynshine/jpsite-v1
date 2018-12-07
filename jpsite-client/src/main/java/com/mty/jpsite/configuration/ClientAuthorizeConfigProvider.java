package com.mty.jpsite.configuration;

import com.mty.jpsite.security.core.authorize.AuthorizeConfigManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 自定义权限拦截配置
 */
public class ClientAuthorizeConfigProvider implements AuthorizeConfigManager {
    /**
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/regist", "/actuator/*")
                .hasRole("ADMIN")
                .anyRequest().permitAll();

    }
}

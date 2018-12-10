package com.mty.jpsite.configuration;

import com.mty.jpsite.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义权限拦截配置
 */
@Component
public class ClientAuthorizeConfigProvider implements AuthorizeConfigProvider {
    /**
     * @param config
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/regist", "/actuator/*")
                .hasRole("VIP")
                .anyRequest().permitAll();
        return false;
    }
}

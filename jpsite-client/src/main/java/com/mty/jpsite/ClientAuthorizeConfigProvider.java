package com.mty.jpsite;

import com.mty.jpsite.security.core.authorize.AuthorizeConfigManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by jiangpeng on 2018/12/4.
 */
public class ClientAuthorizeConfigProvider implements AuthorizeConfigManager {
    /**
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/regist", "/actuator/*")
                .hasRole("ADMIN");

    }
}

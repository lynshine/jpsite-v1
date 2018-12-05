package com.mty.jpsite.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 默认的权限授权配置管理器
 */
@Component
public class JpsiteAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        /**是否有已经存在的配置*/
        boolean existAnyRequestConfig = false;
        /**已经存在的配置名*/
        String existAnyRequestConfigName = null;

        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            /**调用 {@link AuthorizeConfigProvider} 实现子类的config*/
            boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
            /**再次进入则直接throw RuntimeException*/
            if (existAnyRequestConfig && currentIsAnyRequestConfig) {
                throw new RuntimeException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
                        + authorizeConfigProvider.getClass().getSimpleName());
            } else if (currentIsAnyRequestConfig) { /**如果有已经有针对anyRequest的配置，则把existAnyRequestConfig设为true*/
                existAnyRequestConfig = true;
                existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
            }
        }
        /**
         * 最后剩余的anyRequest都要认证
         */
        if (!existAnyRequestConfig) {
            config.anyRequest().authenticated();
        }
    }

}

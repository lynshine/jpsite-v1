package com.mty.jpsite.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * 默认的权限认证配置管理器
 * @author haha
 */
@Component
public class JpsiteAuthorizeConfigManager implements AuthorizeConfigManager {
    /**
     * 注入所有实现了AuthorizeConfigProvider的baen
     */
    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        // 是否有已经存在的配置
        Set<AuthorizeConfigProvider> existAnyRequestConfigSet = new HashSet<>();
        // 已经存在的配置名
        String existAnyRequestConfigName = null;

        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            // 调用 {@link AuthorizeConfigProvider} 的子类实现config
            boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
            //再次进入则直接throw RuntimeException
            if (existAnyRequestConfigSet.contains(authorizeConfigProvider) && currentIsAnyRequestConfig) {
                throw new RuntimeException("重复的anyRequest配置:" + existAnyRequestConfigName + "," + authorizeConfigProvider.getClass().getSimpleName());
            }
            //如果有已经有针对anyRequest的配置，则existAnyRequestConfig为true
            else if (currentIsAnyRequestConfig) {
                existAnyRequestConfigSet.add(authorizeConfigProvider);
                existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
            }
        }
        // 最后剩余的anyRequest都要认证
        if (CollectionUtils.isEmpty(existAnyRequestConfigSet)) {
            config.anyRequest().authenticated();
        }
    }

}

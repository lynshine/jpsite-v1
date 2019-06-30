package com.mty.jpsite.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 权限认证配置管理器接口定义
 * 用于收集系统中所有 AuthorizeConfigProvider实现 并加载其配置
 * @author haha
 */
public interface AuthorizeConfigManager {

    /**
     * 具体认证过程
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}

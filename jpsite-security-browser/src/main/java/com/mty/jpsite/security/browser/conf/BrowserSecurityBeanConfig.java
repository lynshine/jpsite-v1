package com.mty.jpsite.security.browser.conf;

import com.mty.jpsite.security.browser.session.JpsiteExpiredSessionStrategy;
import com.mty.jpsite.security.browser.session.JpsiteInvalidSessionStrategy;
import com.mty.jpsite.security.core.handler.JpsiteLogoutSuccessHandler;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 浏览器环境下扩展点配置，配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全模块默认的配置。
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * session失效时的处理策略配置
     * 结合使用注解@ConditionalOnMissingBean和@Bean,可以做到只有特定名称或者类型的Bean不存在于BeanFactory中时才创建JpsiteInvalidSessionStrategy
     */
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new JpsiteInvalidSessionStrategy(securityProperties);
    }

    /**
     * 并发登录导致前一个session失效时的处理策略配置
     */
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new JpsiteExpiredSessionStrategy(securityProperties);
    }

    /**
     * 退出时的处理策略配置
     */
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new JpsiteLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }

}

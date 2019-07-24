package com.mty.jpsite.security.browser.conf;

import com.mty.jpsite.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 表单登录配置
 * @author haha
 */
@Component
public class FormAuthenticationConfig {
    /**
     * 认证成功处理器
     */
    @Autowired
    protected AuthenticationSuccessHandler jpsiteAuthenticationSuccessHandler;
    /**
     * 认证失败处理器
     */
    @Autowired
    protected AuthenticationFailureHandler jpsiteAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 登录页面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                // 登录处理url
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(jpsiteAuthenticationSuccessHandler)
                .failureHandler(jpsiteAuthenticationFailureHandler);
    }

}

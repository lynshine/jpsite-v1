package com.mty.jpsite.security.core.authorize;

import com.mty.jpsite.security.core.properties.SecurityConstants;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的权限授权配置提供器，安全模块涉及的url的授权配置在这里。
 */
@Component
@Order(Integer.MIN_VALUE)  // 设置执行顺序
public class JpsiteAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignInPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                securityProperties.getBrowser().getSignOutUrl(),
                "/socialRegister",//社交账号注册和绑定页面
                "/user/register",//处理社交注册请求
                "/regist"
        ).permitAll();

        return false;
    }

}

package com.mty.jpsite.security.app.impl;

import com.mty.jpsite.security.core.face.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
    @Autowired
    private AuthenticationSuccessHandler jpsiteAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        /**使用app登录成功处理器*/
        socialAuthenticationFilter.setAuthenticationSuccessHandler(jpsiteAuthenticationSuccessHandler);
    }
}

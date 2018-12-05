package com.mty.jpsite.security.core.face;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * SocialAuthenticationFilter 的后置处理器，用于在不同环境下个性化社交登录的配置
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);

}

package com.mty.jpsite.security.core.config;

import com.mty.jpsite.security.core.face.SocialAuthenticationFilterPostProcessor;
import lombok.Data;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 继承默认的社交登录配置，加入自定义的后处理逻辑
 */
@Data
public class JpSpringSocialConfigurer extends SpringSocialConfigurer {
    /**
     * 过滤处理url
     */
    private String filterProcessesUrl;
    /**
     * {@link SocialAuthenticationFilterPostProcessor} 使用socialAuthenticationFilter的实现类
     */
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public JpSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /**
     * 执行对象的后处理。默认情况下是委托给
     * {@link ObjectPostProcessor}.
     *
     * @param object 来自post
     * @return 返回修改的对象使用
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        /**使用不同环境的后置处理器*/
        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }
}
package com.mty.jpsite.security.social.qq.conf;

import com.mty.jpsite.security.core.face.SocialAuthenticationFilterPostProcessor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 继承默认的社交登录配置，加入自定义的后处理逻辑
 */
public class JpSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public JpSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.SecurityConfigurerAdapter#postProcess(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        /**sh使用后处理器*/
        if(socialAuthenticationFilterPostProcessor != null){
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }

    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
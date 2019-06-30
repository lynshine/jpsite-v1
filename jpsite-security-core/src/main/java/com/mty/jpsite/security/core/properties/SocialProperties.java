package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * 社交登录配置项
 * @author haha
 */
@Data
public class SocialProperties {
    /**
     * 社交登录功能拦截的url
     */
    private String filterProcessesUrl = "/auth";
    /**
     * QQ配置属性
     */
    private QqProperties qq = new QqProperties();
    /**
     * 微信配置属性
     */
    private WeixinProperties weixin = new WeixinProperties();
}
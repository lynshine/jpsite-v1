package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * 微信登录配置项属性
 */
@Data
public class WeixinProperties {
    private String appId;
    private String appSecret;
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";
}
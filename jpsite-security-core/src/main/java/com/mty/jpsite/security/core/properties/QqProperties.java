package com.mty.jpsite.security.core.properties;


import lombok.Data;

/**
 * QQ登录配置项
 * @author haha
 */
@Data
public class QqProperties {
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";
    private String appId;
    private String appSecret;
}


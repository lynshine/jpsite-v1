package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * OAuth2 参数配置
 * @author haha
 */
@Data
public class Oauth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "jpsite";
    /**
     * 客户端配置
     */
    private Oauth2ClientProperties[] clients = {};
}
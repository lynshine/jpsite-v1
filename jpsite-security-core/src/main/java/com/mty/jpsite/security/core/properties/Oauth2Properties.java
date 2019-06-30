package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * OAuth2 参数配置
 */
@Data
public class OAuth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "jpsite";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
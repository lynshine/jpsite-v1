package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * 短信验证码
 * @author haha
 */
@Data
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 4;
    /**
     * 过期时间（秒）
     */
    private int expireIn = 60;
    /**
     * 要拦截的url，多个url用逗号隔开
     */
    private String url;
}
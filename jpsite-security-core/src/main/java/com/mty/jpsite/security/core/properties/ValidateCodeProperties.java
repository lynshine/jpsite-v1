package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * 验证码配置属性
 * @author haha
 */
@Data
public class ValidateCodeProperties {
    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
package com.mty.jpsite.security.core.properties;

import lombok.Data;

/**
 * 图片验证码配置项
 * @author haha
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    /**
     * 图片宽
     */
    private int width = 67;
    /**
     * 图片高
     */
    private int height = 23;

    public ImageCodeProperties() {
        super.setLength(4);
    }
}
package com.mty.jpsite.security.core.properties;

/**
 * 图片验证码配置项
 * Created by jiangpeng on 2018/11/9.
 */
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
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
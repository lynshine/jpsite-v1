package com.mty.jpsite.security.browser.domain;

import com.mty.jpsite.security.core.domain.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * 图片验证码
 * Created by jiangpeng on 2018/11/9.
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}

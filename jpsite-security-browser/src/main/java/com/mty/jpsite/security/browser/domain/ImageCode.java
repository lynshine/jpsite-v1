package com.mty.jpsite.security.browser.domain;

import com.mty.jpsite.security.core.domain.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 图片验证码 extends {@link ValidateCode}
 */
@Data
public class ImageCode extends ValidateCode {
    /**
     * 图片字符流
     */
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}

package com.mty.jpsite.security.browser.face.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import com.mty.jpsite.security.browser.domain.ImageCode;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图片验证码创建实现
 * @author haha
 */
@Component("imageValidateCodeProcess")
public class ImageValidateCodeProcess extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}

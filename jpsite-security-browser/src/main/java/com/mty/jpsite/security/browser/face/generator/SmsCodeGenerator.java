package com.mty.jpsite.security.browser.face.generator;

import com.mty.jpsite.security.browser.face.validateCodeGenerator;
import com.mty.jpsite.security.core.domain.ValidateCode;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码生成器
 * @author haha
 */
@Component
public class SmsCodeGenerator implements validateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 创建验证码
     *
     * @param request
     * @return
     */
    @Override
    public ValidateCode generator(ServletWebRequest request) throws Exception {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
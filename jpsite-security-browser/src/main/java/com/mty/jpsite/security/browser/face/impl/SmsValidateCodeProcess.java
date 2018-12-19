package com.mty.jpsite.security.browser.face.impl;

import com.mty.jpsite.security.core.domain.ValidateCode;
import com.mty.jpsite.security.browser.face.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码创建实现
 */
@Component("smsValidateCodeProcess")
public class SmsValidateCodeProcess extends AbstractValidateCodeProcessor<ValidateCode> {
    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}

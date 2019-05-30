package com.mty.jpsite.security.browser.face.impl;

import com.mty.jpsite.security.browser.face.SmsCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送器
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    private Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);

    @Override
    public void send(String mobile, String code) {
        logger.info("====>向手机：" + mobile + " 发送短信：" + code);
    }
}

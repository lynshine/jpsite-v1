package com.mty.jpsite.security.browser.face.impl;

import com.mty.jpsite.security.browser.face.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 * @author haha
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("====>向手机：{} 发送短信：{}", mobile, code);
    }
}

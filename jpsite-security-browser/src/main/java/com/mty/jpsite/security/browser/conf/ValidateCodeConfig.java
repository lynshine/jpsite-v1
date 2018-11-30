package com.mty.jpsite.security.browser.conf;

import com.mty.jpsite.security.browser.face.SmsCodeSender;
import com.mty.jpsite.security.browser.face.generator.ImageCodeGenerator;
import com.mty.jpsite.security.browser.face.impl.DefaultSmsCodeSender;
import com.mty.jpsite.security.browser.face.validateCodeGenerator;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 */
@Configuration
public class ValidateCodeConfig {
    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 图片验证码图片生成器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public validateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        return imageCodeGenerator;
    }

    /**
     * 短信验证码发送器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}

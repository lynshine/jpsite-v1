package com.mty.jpsite.security.core.config;

import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
/**
 * 这个注解是对@ConfigurationProperties的有效支持。
 * 指定注册SecurityProperties bean
 */
@EnableConfigurationProperties(SecurityProperties.class)        // 让SecurityProperties生效
public class SecurityCoreConfig {
    /**
     * 密码加密类
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

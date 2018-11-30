package com.mty.jpsite.security.core.config;

import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 这个注解是对@ConfigurationProperties的有效支持。
 * 标注有@ConfigurationProperties注解的Beans可以被使用标准的方式注册（使用@Bean注解），
 * 或者，为了方便起见，直接用使用@EnableConfigurationProperties指定注册。
 * 意思是这个注解提供了一种方便直接的注册Bean的方式。
 */
@Configuration
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

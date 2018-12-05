package com.mty.jpsite.security.social.qq.conf;

import com.mty.jpsite.security.core.properties.QQProperties;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QQ登录配置
 */
@Configuration
@ConditionalOnProperty(prefix = "jpsite.security.social.qq", name = "app-id")
public class QQAutoConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 实例化 QQConnectionFactory 连接工厂
     *
     * @return QQConnectionFactory
     */
    @Bean
    public QQConnectionFactory qqConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

}

package com.mty.jpsite.security.social.qq.conf;

import com.mty.jpsite.security.core.properties.QQProperties;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.social.connect.SocialConnectView;
import com.mty.jpsite.security.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;

/**
 * QQ登录配置
 */
@Configuration
@ConditionalOnProperty(prefix = "jpsite.security.social.qq", name = "app-id")
public class QQAuthConfig {
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
    /**
     * /connect/qq POST请求,绑定微信返回connect/qqConnected视图
     * /connect/qq DELETE请求,解绑返回connect/qqConnect视图
     * @return
     */
    @Bean(name = {"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View qqConnectedView() {
        return new SocialConnectView();
    }

}

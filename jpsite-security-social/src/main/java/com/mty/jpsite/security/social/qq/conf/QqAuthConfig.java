package com.mty.jpsite.security.social.qq.conf;

import com.mty.jpsite.security.core.properties.QqProperties;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.social.common.SocialConfig;
import com.mty.jpsite.security.social.connect.SocialConnectView;
import com.mty.jpsite.security.social.qq.connet.QqConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;

/**
 * QQ登录配置
 * @author haha
 */
@Configuration
@ConditionalOnProperty(prefix = "jpsite.security.social.qq", name = "app-id")
public class QqAuthConfig extends SocialConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 实例化 QQConnectionFactory 连接工厂
     *
     * @return QQConnectionFactory
     */
    @Bean
    public QqConnectionFactory qqConnectionFactory() {
        QqProperties qqConfig = securityProperties.getSocial().getQq();
        return new QqConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
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
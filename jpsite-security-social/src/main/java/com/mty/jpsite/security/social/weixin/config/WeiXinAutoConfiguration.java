package com.mty.jpsite.security.social.weixin.config;

import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.core.properties.WeixinProperties;
import com.mty.jpsite.security.social.common.SocialConfig;
import com.mty.jpsite.security.social.connect.SocialConnectView;
import com.mty.jpsite.security.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.web.servlet.View;


/**
 * 微信登录配置
 * @author haha
 */
@Configuration
@ConditionalOnProperty(prefix = "jpsite.security.social.weixin", name = "app-id")
public class WeiXinAutoConfiguration extends SocialConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 实例化 WeixinConnectionFactory 连接工厂
     *
     * @return WeixinConnectionFactory
     */
    @Bean
    public WeiXinConnectionFactory weixinConnectionFactory() {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
        return new WeiXinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(weixinConnectionFactory());
    }

    /**
     * 社交綁定与解绑
     * @return
     */
    @Bean(name = {"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")  //如果有名为weixinConnectedView bean 则覆盖以下默认实现
    public View weixinConnectedView() {
        return new SocialConnectView();
    }

}

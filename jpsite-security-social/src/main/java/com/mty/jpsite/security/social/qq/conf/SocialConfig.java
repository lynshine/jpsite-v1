package com.mty.jpsite.security.social.qq.conf;

import com.mty.jpsite.security.app.impl.AppSocialAuthenticationFilterPostProcessor;
import com.mty.jpsite.security.core.config.JpSpringSocialConfigurer;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.core.face.SocialAuthenticationFilterPostProcessor;
import com.mty.jpsite.security.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 社交登录配置主类
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter implements SocialConfigurer {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private QQConnectionFactory qqConnectionFactory;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 不一定每个系统都会实现这个接口实现第三方登录自动注册
     */
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // Encryptors加密方式
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//        usersConnectionRepository.setTablePrefix("fe");
        if (connectionSignUp != null) {
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return usersConnectionRepository;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator) {
        // todo 數據源暫時寫死
        ConnectController controller = new ConnectController(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator).createConnectionRepository("持悲"));
        return controller;
    }

    /**
     * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
     *
     * @return
     */
    @Bean
    public SpringSocialConfigurer jpSpringSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        JpSpringSocialConfigurer springSocialConfigurer = new JpSpringSocialConfigurer(filterProcessesUrl);
        // 注册页
        springSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        // todo 不知道为什么注解注入没有用
//        springSocialConfigurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        springSocialConfigurer.setSocialAuthenticationFilterPostProcessor(new AppSocialAuthenticationFilterPostProcessor());

        return springSocialConfigurer;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(qqConnectionFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            @Override
            public String getUserId() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null) {
                    throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
                }
                return authentication.getName();
            }
        };
    }

    /**
     * 用来处理注册流程的工具类
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }
}

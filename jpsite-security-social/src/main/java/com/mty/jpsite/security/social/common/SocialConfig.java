package com.mty.jpsite.security.social.common;

import com.mty.jpsite.security.core.config.JpSpringSocialConfigurer;
import com.mty.jpsite.security.core.face.SocialAuthenticationFilterPostProcessor;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.social.qq.connet.QqConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 *
 * @author haha
 */
@Configuration
@EnableSocial
@Slf4j
public class SocialConfig extends SocialConfigurerAdapter implements SocialConfigurer {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private QqConnectionFactory qqConnectionFactory;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 不一定每个系统都会实现这个接口来实现第三方登录自动注册
     */
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;
    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    /**
     * 表UsersConnection 的 Repository
     *
     * @param connectionFactoryLocator
     * @return UsersConnectionRepository
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        /** Encryptors加密方式*/
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 该代码可设置固定表前缀 usersConnectionRepository.setTablePrefix("fe");
        if (connectionSignUp != null) {
            /**设置自动注册策略*/
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return usersConnectionRepository;
    }

    /**
     * 查看社交绑定情况
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator) {
        // todo 數據源暫時寫死,之后可以改成从 SecurityContextHolder取username
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("====>{} 正在查看当前社交账号绑定情况", userName);
        ConnectController controller = new ConnectController(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator).createConnectionRepository("持悲"));
        return controller;
    }

    /**
     * 社交登录配置类，供浏览器或app模块引入使用
     *
     * @return jpSpringSocialConfigurer
     */
    @Bean
    public SpringSocialConfigurer jpSpringSocialConfigurer() {
        // 需要认证的url
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        JpSpringSocialConfigurer springSocialConfigurer = new JpSpringSocialConfigurer(filterProcessesUrl);
        // 注册页url
        springSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        // todo socialAuthenticationFilterPostProcessor可能为null 注解注入没有用  why
        springSocialConfigurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);

        return springSocialConfigurer;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(qqConnectionFactory);
    }

    /**
     * 获取第三方登录信息的userId or userName
     *
     * @return UserIdSource
     */
    @Override
    public UserIdSource getUserIdSource() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
            }
            return authentication.getName();
        };
    }

    /**
     * 用来处理注册流程的工具类
     * 从Session中获取社交账号信息
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}

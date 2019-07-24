package com.mty.jpsite.security.browser.conf;

import com.mty.jpsite.security.browser.authentication.mobile.SmsCodeAuthenticationConfig;
import com.mty.jpsite.security.core.authorize.AuthorizeConfigManager;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * 浏览器环境下安全配置主类 extends {@link WebSecurityConfigurerAdapter}
 */
@Configuration
@EnableWebSecurity
class BrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private SpringSocialConfigurer jpSpringSocialConfigurer;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录验证
        formAuthenticationConfig.configure(http);
        // 验证码
        http.apply(validateCodeSecurityConfig)
                .and()
                //短信验证码
                .apply(smsCodeAuthenticationConfig)
                .and()
                //社交登录
                .apply(jpSpringSocialConfigurer)
                .and()
                // 记住登录
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                // session失效处理策略
                .invalidSessionStrategy(invalidSessionStrategy)
                //session并发
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                //session的数量达到最大数量之后，是否阻止后来的登录行为
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                // 并发登录导致session失效时，默认的处理策略
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                // 退出登录
                .logoutUrl(securityProperties.getBrowser().getSignOutUrl())
                //退出成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                // 删除浏览器上的session记录
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();

        // 权限授权url配置管理器
        authorizeConfigManager.config(http.authorizeRequests());
    }

    /**
     * 记住我功能的token存取器配置
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * 需要配置这个支持oAuth2 password模式
     * support password grant type
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}

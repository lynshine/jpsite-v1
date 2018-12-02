package com.mty.jpsite.security.app.config;

import com.mty.jpsite.security.core.properties.SecurityConstants;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * 浏览器环境下安全配置主类
 */
@Configuration
@EnableWebSecurity
class Oauth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private SpringSocialConfigurer jpSpringSocialConfigurer;
    /**
     * 登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler jpSiteAuthenticationSuccessHandler;
    /**
     * 登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler jpSiteAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(jpSiteAuthenticationSuccessHandler)
                .failureHandler(jpSiteAuthenticationFailureHandler);

        http//.apply(validateCodeSecurityConfig)
                //   .and()
                .apply(jpSpringSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignOutUrl(),
                        SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/user/regist",
//                        "/session/invalid",
                        "/actuator/*"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }


    /**
     * 需要配置这个支持password模式
     * support password grant type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}

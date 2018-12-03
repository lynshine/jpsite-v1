//package com.mty.jpsite.security.app.config;
//
//import com.mty.jpsite.security.app.authentication.OpenIdAuthenticationSecurityConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.social.security.SpringSocialConfigurer;
//
//@Configuration
//@EnableResourceServer
//public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Autowired
//    protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
//
//    @Autowired
//    protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;
//
//    @Autowired
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
//
//    @Autowired
//    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
//
//    @Autowired
//    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
//
//    @Autowired
//    private SpringSocialConfigurer imoocSocialSecurityConfig;
//
//    @Autowired
//    private AuthorizeConfigManager authorizeConfigManager;
//
//    @Autowired
//    private FormAuthenticationConfig formAuthenticationConfig;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        formAuthenticationConfig.configure(http);
//
//        http.apply(validateCodeSecurityConfig)
//                .and()
//                .apply(smsCodeAuthenticationSecurityConfig)
//                .and()
//                .apply(imoocSocialSecurityConfig)
//                .and()
//                .apply(openIdAuthenticationSecurityConfig)
//                .and()
//                .csrf().disable();
//
//        authorizeConfigManager.config(http.authorizeRequests());
//    }
//
//}
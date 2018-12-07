
package com.mty.jpsite.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的UserDetailsService，SocialUserDetailsService实现
 */
@Component("userDetailsService")
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("===> from login userName {}", userName);
        return buildUser(userName);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("====> social login userId {}", userId);
        return buildUser(userId);

    }

    private SocialUserDetails buildUser(String userId) {
        logger.info("登录用户名：" + userId);
        String password = passwordEncoder.encode(env.getProperty("spring.security.user.password"));
        logger.info("数据库密码是：" + password);
        /**
         * ROLE_USER  ouath2 要用
         */
        return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER,ROLE_ADMIN"));
    }
}


package com.mty.jpsite.security.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 自定义的UserDetailsService，SocialUserDetailsService实现
 * @author haha
 */
@Component("userDetailsService")
@Slf4j
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("====> from login userName {}", userName);
        return buildUser(userName);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("====> social login userId {}", userId);
        return socialBuildUser(userId);

    }

    private UserDetails buildUser(String userName) {
        // TODO 模拟数据库登录方式
        log.info("====>登录用户名：" + userName);
        String password = passwordEncoder.encode(env.getProperty("spring.security.user.password"));
        log.info("====>数据库密码是：" + password);
        return new User(userName, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }

    private SocialUserDetails socialBuildUser(String userId) {
        // TODO 模拟数据库登录方式
        log.info("====>登录用户id：" + userId);
        String password = passwordEncoder.encode(env.getProperty("spring.security.user.password"));
        log.info("====>数据库密码是：" + password);
        return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }
}

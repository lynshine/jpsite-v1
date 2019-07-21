
package com.mty.jpsite.security.core.handler;

import com.mty.jpsite.security.core.domain.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自定义的UserDetailsService，SocialUserDetailsService实现
 *
 * @author haha
 */
@Component("userDetailsService")
@Slf4j
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {
    private static final String QUERY_USER_SQL = "select * from user where name=? and password = ?";
    private static final String QUERY_ROLE_SQL = "select r.name from role r left join user_role ur on ur.role_id = r.id where ur.user_id = ?";

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        return getSecurityUserRole(userName);
    }

    private SocialUserDetails socialBuildUser(String userId) {
        return (SocialUser) getSecurityUserRole(userId);
    }

    private User getSecurityUserRole(String userName) throws UsernameNotFoundException {
        log.info("====>登录用户名：{}", userName);
        String password = passwordEncoder.encode(httpServletRequest.getParameter("password"));
        log.info("====>数据库密码是：{}", password);
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<>(MyUser.class);
        MyUser myUser = jdbcTemplate.queryForObject(QUERY_USER_SQL, rowMapper, userName, password);
        if (myUser == null) {
            throw new UsernameNotFoundException("当前用户不存在");
        }
        List<String> roleNames = jdbcTemplate.queryForList(QUERY_ROLE_SQL, String.class, myUser.getId());

        return new User(userName, password, true, true, true,
                true, AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", roleNames)));
    }
}

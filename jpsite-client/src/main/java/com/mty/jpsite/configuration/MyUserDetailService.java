
package com.mty.jpsite.configuration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.server.entity.role.Role;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.entity.userRole.UserRole;
import com.mty.jpsite.server.service.role.RoleService;
import com.mty.jpsite.server.service.user.UserService;
import com.mty.jpsite.server.service.userRole.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的UserDetailsService，SocialUserDetailsService实现
 */
@Component("userDetailsService")
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

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

    private SocialUserDetails buildUser(String userId) throws UsernameNotFoundException {
        logger.info("登录用户名：" + userId);

        User user = userService.getById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", userId);
        /**查找对应角色权限*/
        List<UserRole> list = userRoleService.list(queryWrapper);
        Collection<Role> roles = roleService.listByIds(list.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        String roleNames = roles.stream().map(Role::getName).collect(Collectors.joining(","));
        /**
         * ROLE_USER  ouath2 要用
         */
        return new SocialUser(userId,
                user.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(roleNames)
        );
    }
}

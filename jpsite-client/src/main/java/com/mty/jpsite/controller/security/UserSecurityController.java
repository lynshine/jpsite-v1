package com.mty.jpsite.controller.security;

import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.server.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhailiang
 */
@RestController
@RequestMapping("/userSecurity")
@Slf4j
public class UserSecurityController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getName();
        //todo 瀏覽器使用，appSingUpUtils  providerSignInUtils 只能用一個
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }
}

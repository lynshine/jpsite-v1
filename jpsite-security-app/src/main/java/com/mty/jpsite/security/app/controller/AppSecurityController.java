/**
 *
 */
package com.mty.jpsite.security.app.controller;

import com.mty.jpsite.security.app.common.AppSingUpUtils;
import com.mty.jpsite.security.core.controller.SocialController;
import com.mty.jpsite.security.core.domain.SocialUserInfo;
import com.mty.jpsite.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhailiang
 */
@RestController
public class AppSecurityController extends SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSingUpUtils appSingUpUtils;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端
     *
     * @param request
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_SIGN_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        // 用spring 的providerSignInUtils 把跳转之前放到session里的信息拿出来， 因为在app环境下每次请求都是一个新的session空的，
        // 在第一次跳转的时候是可以拿出来信息的，但是后面的session就拿不出来了， 所以先从session把需要的数据取出拼好
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        appSingUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
        return buildSocialUserInfo(connection);
    }

}

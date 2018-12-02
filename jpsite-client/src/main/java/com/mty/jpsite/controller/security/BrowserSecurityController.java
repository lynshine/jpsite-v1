package com.mty.jpsite.controller.security;

import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.properties.SecurityConstants;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.security.core.domain.SocialUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // 原请求信息的缓存及恢复
    private RequestCache requestCache = new HttpSessionRequestCache();
    // 用于重定向
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证的时候，跳转过来
     *
     * @param request
     * @param response
     * @return
     **/

    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SecurityResponse requireAuthenication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:" + targetUrl);
            //if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            // }
        }

        return new SecurityResponse("访问的服务需要身份认证，请引导用户到登录页");
    }

    @GetMapping("/getMe")
    public Object getMe(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/getSimpleMe")
    public Object getSimpleMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    /**
     * 用户第一次社交登录时，会引导用户进行用户注册或绑定，此服务用于在注册或绑定页面获取社交网站用户信息
     *
     * @param request
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SecurityResponse sessionInvalid() {
        String message = "session失效";
        return new SecurityResponse(message);
    }
}

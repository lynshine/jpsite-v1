package com.mty.jpsite.controller.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mty.jpsite.security.core.controller.BaseSocialController;
import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.domain.SocialUserInfo;
import com.mty.jpsite.security.core.properties.SecurityConstants;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import com.mty.jpsite.server.entity.user.User;
import com.mty.jpsite.server.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author haha
 */
@Api(description = "浏览器安全控制类")
@RestController
@Slf4j
public class BrowserSecurityController extends BaseSocialController {
    /**
     * 原请求内容的缓存及恢复
     */
    private RequestCache requestCache = new HttpSessionRequestCache();
    /**
     * 用于重定向
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private UserService userService;

    @ApiOperation(value = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
            notes = "当需要身份认证的时候，跳转过来")
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SecurityResponse requireAuthenication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** 获取登录请求的内容*/
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("====>引发跳转的请求是:" + targetUrl);
//            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
//                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
//            }
//            else if (StringUtils.endsWithIgnoreCase(targetUrl, ".json")) {
//                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
//            } else {
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
//            }
        }

        return new SecurityResponse("====>访问的服务需要身份认证，请引导用户到登录页");
    }

    @ApiOperation(value = "/getMe",
            notes = "获取完整的认证后信息")
    @GetMapping("/getMe")
    @ResponseBody
    public Object getMe(Authentication authentication) {
        /**
         * 效果等同于SecurityContextHolder.getContext().getAuthentication()
         */
        return authentication;
    }

    @ApiOperation(value = "/getSimpleMe",
            notes = "获取简单的认证后信息")
    @GetMapping("/getSimpleMe")
    public Object getSimpleMe(@AuthenticationPrincipal UserDetails userDetails) {
        /**
         * 效果等同于SecurityContextHolder.getContext().getAuthentication().getDetails();
         */
        return userDetails;
    }

    @ApiOperation(value = SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
            notes = "获取第三方用户信息的，可用于用户第一次社交登录时，会引导用户进行用户注册或绑定，" +
                    "此服务用于在注册或绑定页面获取社交网站用户信息）")
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        /**
         * 获取connectionFactory，QQ则是QQConnectionFactory，微信是WeixinConnectionFactory
         */
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        return buildSocialUserInfo(connection);
    }

    @ApiOperation(value = "/session/invalid",
            notes = "退出登录")
    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SecurityResponse sessionInvalid() {
        String message = "session失效";
        return new SecurityResponse(message);
    }

    /**
     * //社交账号注册和绑定页面
     * @param request
     * @param map
     * @return
     */
    @GetMapping(value = "/socialRegister")
    @ResponseBody
    public SocialUserInfo socialRegister(HttpServletRequest request, Map<String, Object> map) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());//哪一个服务提供商
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());//openid
        userInfo.setNickname(connection.getDisplayName());//名称
        userInfo.setHeadImg(connection.getImageUrl());//显示头像
        return userInfo;
    }

    /**
     * 处理社交注册请求
     * @param user
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/user/register")
    public String register(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户名
        String phone = user.getPhone();
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("phone",phone);
        User result =  userService.getOne(wrapper);
        if(result==null){
            //如果为空则注册用户
            userService.save(user);
        }
        //将业务系统的用户与社交用户绑定
        providerSignInUtils.doPostSignUp(user.getId().toString(), new ServletWebRequest(request));
        //跳转到index
        return "redirect:/index";
    }
}

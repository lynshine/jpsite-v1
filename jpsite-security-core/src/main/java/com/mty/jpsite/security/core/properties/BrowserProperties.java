package com.mty.jpsite.security.core.properties;


import com.mty.jpsite.security.core.enums.LoginResponseType;
import lombok.Data;

/**
 * 浏览器环境配置项
 *
 * @author haha
 */
@Data
public class BrowserProperties {
    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();
    /**
     * 注册地址
     */
    private String signUpUrl = "/sign_up.html";
    /**
     * 默认登录页面
     */
    private String loginPage = "/login_in.html";
    /**
     * 默认记住时间（秒）
     */
    private int rememberMeSeconds = 3600;
    /**
     * 默认登录返回类型JSON
     */
    private LoginResponseType loginResponseType = LoginResponseType.JSON;
    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;
    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
}
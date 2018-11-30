package com.mty.jpsite.security.social.qq.connet;

import com.mty.jpsite.security.social.qq.api.QQ;
import com.mty.jpsite.security.social.qq.api.QQImpl;
import com.mty.jpsite.security.social.qq.conf.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * QQ登录服务提供者
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";  //认证服务器
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";   // 获取accessToken

    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}

package com.mty.jpsite.security.social.qq.connet;

import com.mty.jpsite.security.social.qq.api.Qq;
import com.mty.jpsite.security.social.qq.api.QqImpl;
import com.mty.jpsite.security.social.qq.conf.QqOauth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * QQ登录服务提供者
 * @author haha
 */
public class QqServiceProvider extends AbstractOAuth2ServiceProvider<Qq> {
    /**
     * 认证服务器
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /**
     * 获取accessToken
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    /**
     * 构造方法
     * @param appId
     * @param appSecret
     */
    public QqServiceProvider(String appId, String appSecret) {
        super(new QqOauth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public Qq getApi(String accessToken) {
        return new QqImpl(accessToken, appId);
    }
}

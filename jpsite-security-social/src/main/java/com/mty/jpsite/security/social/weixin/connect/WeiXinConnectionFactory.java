package com.mty.jpsite.security.social.weixin.connect;

import com.mty.jpsite.security.social.weixin.api.WeiXin;
import com.mty.jpsite.security.social.weixin.api.WeiXinAccessGrant;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;


/**
 * 微信连接工厂
 * @author haha
 */
public class WeiXinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {

    /**
     * @param appId
     * @param appSecret
     */
    public WeiXinConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiXinServiceProvider(appId, appSecret), new WeiXinAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeiXinAccessGrant) {
            return ((WeiXinAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeiXin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeiXin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    /**
     * 使用自己定义的ApiAdapter，默认的ApiAdapter是单例的
     * openId有多个，所以用多实例
     * @param providerUserId
     * @return
     */
    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }


}

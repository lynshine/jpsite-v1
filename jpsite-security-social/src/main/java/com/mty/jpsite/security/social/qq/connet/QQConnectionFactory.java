package com.mty.jpsite.security.social.qq.connet;

import com.mty.jpsite.security.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * QQConnectionFactory 继承OAuth2ConnectionFactory
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * @param providerId 服务商用户ID
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}

package com.mty.jpsite.security.social.qq.connet;

import com.mty.jpsite.security.social.qq.api.Qq;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * QQConnectionFactory 继承OAuth2ConnectionFactory
 * @author haha
 */
public class QqConnectionFactory extends OAuth2ConnectionFactory<Qq> {
    /**
     * @param providerId 服务商用户ID
     * @param appId
     * @param appSecret
     */
    public QqConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QqServiceProvider(appId, appSecret), new QqAdapter());
    }

}

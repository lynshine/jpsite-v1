package com.mty.jpsite.security.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * QQImpl extends {@link AbstractOAuth2ApiBinding} implements {@link Qq}
 * @author haha
 */
public class QqImpl extends AbstractOAuth2ApiBinding implements Qq {
    private Logger logger = LoggerFactory.getLogger(QqImpl.class);

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;
    private ObjectMapper objectMapper = new ObjectMapper();

    public QqImpl(String accessToken, String appId) {
        /**作为查询参数挂在请求上*/
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        /**请求发送获取openId*/
        String result = getRestTemplate().getForObject(url, String.class);
        /**字符串截取获取openid*/
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    /**
     * 获取QQUserInfo
     *
     * @return QQUserInfo
     */
    @Override
    public QqUserInfo getUserInfo() {
        /**拼接url*/
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        logger.info("====>getUserInfo() by QQUserInfo: {}", result);

        QqUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QqUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("====>获取用户信息失败：{}", e);
        }
    }
}

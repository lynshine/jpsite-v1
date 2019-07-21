package com.mty.jpsite.security.core.controller;

import com.mty.jpsite.security.core.domain.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * 抽象社交控制类 SocialController
 *
 * @author haha
 */
public abstract class BaseSocialController {

    /**
     * 根据Connection信息组装SocialUserInfo
     *
     * @param connection socialConnection 第三方连接
     * @return SocialUserInfo
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        /*
         * 第三方应用id
         */
        userInfo.setProviderId(connection.getKey().getProviderId());
        /*
         * 第三方用户id
         */
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        /*
         * 昵称
         */
        userInfo.setNickname(connection.getDisplayName());
        /*
         * 头像图片url
         */
        userInfo.setHeadImg(connection.getImageUrl());
        return userInfo;
    }

}

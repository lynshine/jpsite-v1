package com.mty.jpsite.security.social.qq.connet;

import com.mty.jpsite.security.social.qq.api.QQ;
import com.mty.jpsite.security.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * QQ登录适配器
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = null;
        try {
            userInfo = api.getUserInfo();
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        /**个人主页*/
        values.setProfileUrl(null);
        /**服务商用户ID*/
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }

}

package com.mty.jpsite.security.social.weixin.connect;

import com.mty.jpsite.security.social.weixin.api.WeiXin;
import com.mty.jpsite.security.social.weixin.api.WeiXinUserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * @author haha
 */
@AllArgsConstructor
@NoArgsConstructor
public class WeiXinAdapter implements ApiAdapter<WeiXin> {
    private String openId;

    /**
     * @param api
     * @return
     */
    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    /**
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    /**
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    /**
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(WeiXin api, String message) {
        //do nothing
    }
}

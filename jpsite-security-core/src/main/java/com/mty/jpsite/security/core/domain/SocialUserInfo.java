package com.mty.jpsite.security.core.domain;

/**
 * SocialUserInfo
 */
public class SocialUserInfo {
    /**
     * 第三方机构id
     */
    private String providerId;
    /**
     * 第三方的用户id
     */
    private String providerUserId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headimg;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

}

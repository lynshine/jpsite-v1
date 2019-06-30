package com.mty.jpsite.security.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 社交用户信息
 * @author haha
 */
@Data
public class SocialUserInfo implements Serializable {
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
    private String headImg;
}
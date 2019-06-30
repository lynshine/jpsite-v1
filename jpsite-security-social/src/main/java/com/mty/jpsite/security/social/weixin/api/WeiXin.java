package com.mty.jpsite.security.social.weixin.api;


/**
 * 微信API调用接口
 * @author haha
 */
public interface WeiXin {
    /**
     * 获取 WeixinUserInfo
     * @return WeixinUserInfo
     */
    WeiXinUserInfo getUserInfo(String openId);
}
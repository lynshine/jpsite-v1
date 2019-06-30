package com.mty.jpsite.security.social.qq.api;

import java.io.IOException;

/**
 * QQ interface
 */
public interface QQ {
    /**
     * 获取 QQUserInfo
     * @return QQUserInfo
     * @throws IOException
     */
    QQUserInfo getUserInfo() throws IOException;
}

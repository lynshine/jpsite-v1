package com.mty.jpsite.security.social.qq.api;

import java.io.IOException;

/**
 * qq interface
 */
public interface QQ {
    /**
     * 获取userInfo
     * @return
     * @throws IOException
     */
    QQUserInfo getUserInfo() throws IOException;
}

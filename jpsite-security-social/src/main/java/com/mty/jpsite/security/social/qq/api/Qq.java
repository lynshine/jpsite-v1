package com.mty.jpsite.security.social.qq.api;

import java.io.IOException;

/**
 * QQ interface
 * @author haha
 */
public interface Qq {
    /**
     * 获取 QqUserInfo
     * @return QqUserInfo
     * @throws IOException
     */
    QqUserInfo getUserInfo() throws IOException;
}

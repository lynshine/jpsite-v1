package com.mty.jpsite.security.browser.session;

import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发登录导致session失效时，默认的处理策略
 */
public class JpsiteExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public JpsiteExpiredSessionStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    /**
     * 检测到session过期时
     *
     * @param event
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    /**
     * session失效是并发登录导致的
     *
     * @return true
     */
    @Override
    protected boolean isConcurrency() {
        return true;
    }

}

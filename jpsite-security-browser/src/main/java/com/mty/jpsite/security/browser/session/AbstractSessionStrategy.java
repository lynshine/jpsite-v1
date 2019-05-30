package com.mty.jpsite.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 抽象的session失效处理器
 *
 * @author zhailiang
 *
 */
@Data
public class AbstractSessionStrategy {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 跳转的url
     */
    private String destinationUrl;
    /**
     * 系统配置信息
     */
    private SecurityProperties securityPropertie;
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(SecurityProperties securityPropertie) {
        /**
         * session失效时跳转的地址
         */
        String invalidSessionUrl = securityPropertie.getBrowser().getSession().getSessionInvalidUrl();

        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");

        this.destinationUrl = invalidSessionUrl;
        this.securityPropertie = securityPropertie;
    }

    /**
     * 当session失效时
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("session失效");

        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            /**
             * 如果sourceUrl是登录页面或者退出成功时指定跳转的url，则targetUrl = sourceUrl
             */
            if (StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignInPage())
                    || StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignOutUrl())) {
                targetUrl = sourceUrl;
            } else {
                /** session失效时跳转的地址*/
                targetUrl = destinationUrl;
            }
            logger.info("====>跳转到:" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }

    }

    /**
     * @param request
     * @return
     */
    protected Object buildResponseContent(HttpServletRequest request) {
        request.getSession();
        String message = "session已失效";
        if (isConcurrency()) {
            message = message + "，有可能是并发登录导致的";
        }
        return new SecurityResponse(message);
    }

    /**
     * session失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency() {
        // todo session失效是否是并发导致的
        return false;
    }
}

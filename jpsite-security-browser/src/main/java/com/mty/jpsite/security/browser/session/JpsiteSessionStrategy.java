package com.mty.jpsite.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * session失效处理器基类
 *
 * @author zhailiang
 *
 */
@Data
@Slf4j
public class JpsiteSessionStrategy {
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
    /**
     * 操作session的
     */
    private SessionRegistry sessionRegistry = new SessionRegistryImpl();

    public JpsiteSessionStrategy(SecurityProperties securityPropertie) {
        /*
          session失效时跳转的地址
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
        log.info("====>session失效");

        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            /*
              如果sourceUrl是登录页面或者退出成功时指定跳转的url，则targetUrl = sourceUrl
             */
            if (StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignInPage())
                    || StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSignOutUrl())) {
                targetUrl = sourceUrl;
            } else {
                // session失效时跳转的地址
                targetUrl = destinationUrl;
            }
            log.info("====>跳转到:{}", targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            SecurityResponse result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }

    }

    /**
     * @param request
     * @return
     */
    protected SecurityResponse buildResponseContent(HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        String message = "session已失效";
        if (isConcurrency(request)) {
            message = message + "，有可能是并发登录导致的";
        }
        return new SecurityResponse(message);
    }

    /**
     * session失效是否是并发导致的
     *
     * @return
     */
    protected boolean isConcurrency(HttpServletRequest request) {
        User loginedUser = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        List<SessionInformation> sessionsInfos = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), true);
        String currentSessionId = sessionsInfos.get(0).getSessionId();

        if (CollectionUtils.isNotEmpty(sessionsInfos)) {
            sessionRegistry.registerNewSession(request.getSession().getId(), sc.getAuthentication().getPrincipal());
            sessionsInfos = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), false);
        }
        List<Object> o = sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            if (principal instanceof User && (loginedUser.getUsername().equals(((User) principal).getUsername()))) {
                List<SessionInformation> oldSessionsInfos = sessionRegistry.getAllSessions(principal, false);
                if (CollectionUtils.isNotEmpty(oldSessionsInfos) && oldSessionsInfos.get(0).getSessionId().equals(currentSessionId)) {
                    for (SessionInformation sessionInformation : sessionsInfos) {
                        //当前session失效
                        sessionInformation.expireNow();
                        sc.setAuthentication(null);
                        sessionRegistry.removeSessionInformation(currentSessionId);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

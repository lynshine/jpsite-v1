package com.mty.jpsite.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认的退出成功处理器，
 * 如果设置了jpsite.security.browser.signOutUrl，则跳到配置的地址上，
 * 如果没配置，则返回json格式的响应。
 *
 * @author jiangpeng
 */
@Slf4j
public class JpsiteLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     * 退出成功跳转url
     */
    private String signOutSuccessUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public JpsiteLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        log.info("====>退出成功");

        // 如果signOutSuccessUrl为空，则返回json格式的响应。
        if (StringUtils.isEmpty(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SecurityResponse("退出成功")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }
    }
}

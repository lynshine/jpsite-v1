package com.mty.jpsite.security.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.security.core.enums.LoginResponseType;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 * 继承默认的spring security 成功处理器SavedRequestAwareAuthenticationSuccessHandler
 */
@Component
public class JpsiteAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(JpsiteAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 成功逻辑处理
     *
     * @param authentication 接口封装认证信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("====>登录成功--LoginResponseType: " + securityProperties.getBrowser().getLoginResponseType());

        /**判断登录请求类型*/
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            /**将authentication认证信息转换为json格式的字符串写到response里面去*/
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }

    }
}

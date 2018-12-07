package com.mty.jpsite.security.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mty.jpsite.security.core.domain.SecurityResponse;
import com.mty.jpsite.security.core.enums.LoginResponseType;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录失败处理器
 */
@Component
class JpsiteAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(JpsiteAuthenticationSuccessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 失败逻辑处理
     *
     * @param authenticationException 接口封装认证失败信息
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        logger.info("登录失败--loginType: {}", securityProperties.getBrowser().getLoginResponseType());

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        /**判断登录请求类型*/
        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())) {
            response.setContentType("application/json;charset=UTF-8");
            /**将authenticationException失败信息转换为json格式的字符串写到response里面去*/
            response.getWriter().write(objectMapper.writeValueAsString(new SecurityResponse(authenticationException.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, authenticationException);
        }
    }
}
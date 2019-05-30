package com.mty.jpsite.security.browser.handler;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码处理器
 */
public interface ValidateCodeProcess {
    /**
     * 生成验证码逻辑
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}

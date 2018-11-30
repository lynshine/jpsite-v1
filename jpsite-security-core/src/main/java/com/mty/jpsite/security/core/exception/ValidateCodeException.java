package com.mty.jpsite.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码异常
 * ValidateCodeException，继承AuthenticationException
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}

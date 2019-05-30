package com.mty.jpsite.security.core.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

/**
 * 自定义验证码异常
 * ValidateCodeException，继承自 {@AuthenticationException}
 */
public class ValidateCodeException extends AuthenticationException implements Serializable {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}

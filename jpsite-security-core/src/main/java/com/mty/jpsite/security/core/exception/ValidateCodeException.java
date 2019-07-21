package com.mty.jpsite.security.core.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

/**
 * 自定义验证码异常
 * ValidateCodeException，继承自 {@AuthenticationException}
 * @author haha
 */
public class ValidateCodeException extends AuthenticationException implements Serializable {
    /**
     * 构造方法，调用父类的构造方法
     * @param msg message
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }
}

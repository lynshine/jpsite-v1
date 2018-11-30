package com.mty.jpsite.controller;

import com.mty.jpsite.domain.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理 controller  增强类
 */
@RestControllerAdvice
public class BaseExceptionController {
    @ExceptionHandler(value = Exception.class)
    public BaseException exception(Exception e) {
        e.printStackTrace();
        return new BaseException();
    }
}

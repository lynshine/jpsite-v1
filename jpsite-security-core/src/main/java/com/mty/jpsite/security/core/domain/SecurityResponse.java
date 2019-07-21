package com.mty.jpsite.security.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 认证响应
 * @author haha
 */
@Data
// AccessLevel构造方法访问权限
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SecurityResponse implements Serializable {
    /**
     * 响应内容
     */
    private Object content;
}
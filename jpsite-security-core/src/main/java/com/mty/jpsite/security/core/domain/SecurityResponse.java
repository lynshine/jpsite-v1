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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SecurityResponse implements Serializable {
    private Object content;
}
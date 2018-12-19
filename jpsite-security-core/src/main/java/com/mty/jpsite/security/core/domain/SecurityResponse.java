package com.mty.jpsite.security.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * SecurityResponse content 安全响应
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SecurityResponse implements Serializable {
    private Object content;
}